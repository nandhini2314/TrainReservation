package com.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainapp.model.LocationModel;
import com.trainapp.repository.LocationRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<LocationModel> getAll() {
        return repository.findAll();
    }

    public LocationModel getById(String id) {
        return repository.findById(id).get();
    }

    public LocationModel getByName(String name) {
        return repository.findByName(name);
    }

    public List<String> getStationsByPathName(String name){
        return repository.findByName(name).getStationList();
    }

    public List<String> getAllPaths(){
        List<LocationModel> locations = repository.findAll();
        Set<String> uniquePathNames = locations.stream().map(LocationModel::getName).collect(Collectors.toSet());
        return new ArrayList<>(uniquePathNames);
    }

    public String addPath(LocationModel obj) {
        repository.save(obj);
        return "DONE :D";
    }

}
