package com.trainapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trainapp.dto.ReservationRequest;
import com.trainapp.dto.ReservationResponse;
import com.trainapp.model.ReservationDetails;
import com.trainapp.service.ReservationService;
import com.trainapp.dto.ReservationFinalRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    // @SuppressWarnings({ "null", "unchecked" })
    // @PostMapping("/")
    // public ReservationResponse reservationBooking(@RequestBody ReservationRequest
    // reservationRequest) {
    // RestTemplate restTemplate = new RestTemplate();
    // System.out.println(reservationRequest);
    // Map<String, Object> locationResponse = restTemplate.getForObject(
    // "http://localhost:8084/api/location/" +
    // reservationRequest.getTrainPathName(), Map.class);
    // List<String> stationList = (List<String>)
    // locationResponse.get("stationList");
    // int stationA = stationList.indexOf(reservationRequest.getOrigin());
    // int stationB = stationList.indexOf(reservationRequest.getDestination());
    // Map<String, Object> trainResponse = restTemplate.getForObject(
    // "http://localhost:8081/api/train/" + reservationRequest.getTrainId(),
    // Map.class);
    // int trainFare = (Integer) trainResponse.get("trainFare");
    // float totalFare = Math.abs(stationA - stationB) *
    // reservationRequest.getPassengerName().size() * trainFare;
    // reservationService.createReservation(reservationRequest);
    // ReservationResponse reservationResponse = new ReservationResponse();
    // reservationResponse.setTrainId(reservationRequest.getTrainId());
    // reservationResponse.setTrainRouteName(reservationRequest.getTrainPathName());
    // reservationResponse.setOrigin(reservationRequest.getOrigin());
    // reservationResponse.setDestination(reservationRequest.getDestination());
    // reservationResponse.setPassengerName(reservationRequest.getPassengerName());
    // reservationResponse.setQuantity(reservationRequest.getPassengerName().size());
    // reservationResponse.setFare(totalFare);
    // return reservationResponse;
    // }

    @SuppressWarnings({ "null", "unchecked" })
    @PostMapping("/getTotalFare")
    public float getCost(@RequestBody ReservationRequest reservationRequest) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> locationResponse = restTemplate.getForObject(
                "http://localhost:8084/api/location/" + reservationRequest.getTrainPathName(), Map.class);
        List<String> stationList = (List<String>) locationResponse.get("stationList");
        System.out.println(stationList);
        int stationA = stationList.indexOf(reservationRequest.getOrigin());
        int stationB = stationList.indexOf(reservationRequest.getDestination());
        Map<String, Object> trainResponse = restTemplate.getForObject(
                "http://localhost:8081/api/train/" + reservationRequest.getTrainId(), Map.class);
        int trainFare = (Integer) trainResponse.get("trainFare");
        float totalFare = Math.abs(stationA - stationB) * reservationRequest.getPassengerName().size() * trainFare;
        return totalFare;
    }

    @GetMapping
    public List<ReservationDetails> listit() {
        return reservationService.getall();
    }

    @SuppressWarnings({ "null" })
    @PostMapping("/checkAvailabilityAndReserve")
    public ResponseEntity<Map<String, Object>> checkReservationAvailability(
            @RequestBody ReservationFinalRequest finalRequest) {
        RestTemplate restTemplate = new RestTemplate();
        // Get train capacity from the timetable service
        int trainCapacity = restTemplate.postForObject(
                "http://localhost:8083/api/timetable/getCapacityById/" + finalRequest.getTimetableId(),
                null, Integer.class);
        // Calculate remaining capacity after considering current bookings
        int remainingCapacity = trainCapacity - finalRequest.getPassengerName().size();

        if (remainingCapacity < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error",
                            "Cannot book because available capacity is " + trainCapacity));
        } else {
            // Prepare the payload for updating the capacity
            Map<String, Object> updatePayload = new HashMap<>();
            updatePayload.put("timetableId", finalRequest.getTimetableId());
            updatePayload.put("subtractThisCapacityFromTheModel", finalRequest.getPassengerName().size());

            ResponseEntity<String> updateResponse = restTemplate.exchange(
                    "http://localhost:8083/api/timetable/updateCapacity",
                    HttpMethod.PUT,
                    new HttpEntity<>(updatePayload),
                    String.class);
            // Check if the capacity update was successful
            if (updateResponse.getStatusCode() == HttpStatus.OK && "DONE".equals(updateResponse.getBody())) {
                // If update was successful, proceed with reservation creation
                String key = reservationService.createReservation(finalRequest);
                Map<String, Object> responseMap = Collections.singletonMap("key", key);
                return ResponseEntity.ok(responseMap);
            } else {
                // If update failed, return an error response
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "Failed to update capacity"));
            }
        }
    }

}
