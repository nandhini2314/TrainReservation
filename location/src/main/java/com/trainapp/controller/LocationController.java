package com.trainapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trainapp.model.LocationModel;
import com.trainapp.service.LocationService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping
    public List<LocationModel> getAllPaths() {
        return service.getAll();
    }

    @PostMapping
    public String postPath(@RequestBody LocationModel obj) {
        return service.addPath(obj);

    }
    @GetMapping("/displayMessage") 
    public ResponseEntity<String> showMessage(){ 
        return ResponseEntity.ok("Microservice 1 controller executed"); 
    }

    @GetMapping("/{name}")
    public LocationModel getLocationByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/getStations/{name}")
    public List<String> getStationsByPathName(@PathVariable String name){
        return service.getStationsByPathName(name);
    }

    @GetMapping("/id/{id}")
    public LocationModel getLocationById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/allPathNames")
    public List<String> getAllPathNames(){
        return service.getAllPaths();
    }

}
