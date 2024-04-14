package com.trainapp.service;

import com.trainapp.dto.ReservationResponse;
import com.trainapp.model.ReservationDetails;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainapp.dto.ReservationFinalRequest;
import com.trainapp.dto.ReservationRequest;
import com.trainapp.repository.ReservationRepository;

import java.util.List;

@Service
@Slf4j
public class ReservationService {
    @Autowired
    private ReservationRepository repository;

    @SuppressWarnings("null")
    public String createReservation(ReservationFinalRequest reservation) {
        ReservationDetails reservationSave = ReservationDetails.builder()
                .timetableId(reservation.getTimetableId())
                .origin(reservation.getOrigin())
                .destination(reservation.getDestination())
                .passengerName(reservation.getPassengerName())
                .trainId(reservation.getTrainId())
                .trainPathName(reservation.getTrainPathName())
                .totalFare(reservation.getTotalFare())
                .build();
        reservationSave = repository.save(reservationSave);
        log.info(reservationSave + " has been added");
        return reservationSave.getId();
    }


    public List<ReservationDetails> getall(){
        return repository.findAll();
    }

    private ReservationResponse mapToReservationResponse(ReservationDetails reservation) {
        return ReservationResponse.builder()
                .origin(reservation.getOrigin())
                .destination(reservation.getDestination())
                .passengerName(reservation.getPassengerName())
                .build();
    }
}
