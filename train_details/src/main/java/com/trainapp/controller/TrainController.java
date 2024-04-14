package com.trainapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trainapp.dto.TrainRequest;
import com.trainapp.dto.TrainResponse;
import com.trainapp.service.TrainService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/addTrain")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createTrain(@RequestBody TrainRequest trainRequest) {
        trainService.createTrain(trainRequest);
    }

    @PutMapping("/updateTrain")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTrain(@RequestBody TrainRequest trainRequest) {
        trainService.updateTrain(trainRequest);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainResponse> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/{Id}")
    public TrainResponse getSpecificTrain(@PathVariable String Id) {
        return trainService.getSpecificTrain(Id);
    }

    @DeleteMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTrain(@PathVariable String Id) {
        trainService.deleteTrain(Id);
        return new String(Id + " has been deleted");
    }

    @GetMapping("/getCapacity/{Id}")
    public int getCapacity(@PathVariable String Id){
        return trainService.getCapacity(Id);
    }
}
