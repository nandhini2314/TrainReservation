package com.trainapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trainapp.dto.FilterRequest;
import com.trainapp.dto.ReservationRequest;
import com.trainapp.dto.TTRequest;
import com.trainapp.dto.TTResponse;
import com.trainapp.dto.UpdateCapacity;
import com.trainapp.model.TTModel;
import com.trainapp.service.TTService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/timetable")
public class TTController {
    @Autowired
    private TTService service;

    @GetMapping("/{name}")
    public List<TTResponse> getTrainTimeTable(@PathVariable String name) {
        return service.getByName(name);
    }
    @GetMapping("/displayMessage") 
    public ResponseEntity<String> showMessage(){ 
        return ResponseEntity.ok("Microservice 2 controller executed"); 
    }

    @PostMapping("/getCapacityById/{id}")
    public int getCapacity(@PathVariable String id){
        return service.getCapacityById(id);
    }

    @PutMapping("/updateCapacity")
    public String updateCapacity(@RequestBody UpdateCapacity obj){
        service.updateCapacity(obj);
        return "DONE";
    }

    @PostMapping
    public String postObj(@RequestBody TTModel obj) {
        service.addTT(obj);
        return "DONE :D";
    }

    @GetMapping
    public List<TTResponse> getAllTT() {
        return service.getAllTT();
    }

    @GetMapping("/lol")
    public List<TTModel> getMethodName() {
        return service.getAllBypass();
    }
    

    @DeleteMapping("/{id}")
    public String deleteTT(@PathVariable String id) {
        return service.deleteTT(id);
    }

    @PostMapping("/filterTT")
    public List<TTModel> filterTT(@RequestBody FilterRequest obj){
        return service.filterTT(obj);
    }

}
