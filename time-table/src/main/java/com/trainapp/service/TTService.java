package com.trainapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainapp.dto.FilterRequest;
import com.trainapp.dto.TTRequest;
import com.trainapp.dto.TTResponse;
import com.trainapp.dto.UpdateCapacity;
import com.trainapp.model.TTModel;
import com.trainapp.repository.TTRepo;

@Service
public class TTService {

    @Autowired
    private TTRepo repository;

    public List<TTResponse> getAllTT() {
        return repository.findAll().stream().map(this::mapTTToResponse).toList();
    }

    public List<TTResponse> getByName(String name) {
        List<TTModel> model_obj = repository.findByTrainId(name);
        return model_obj.stream().map(this::mapTTToResponse).toList();

    }
    public List<TTModel> getAllBypass(){
        return repository.findAll();
    }
    public TTResponse mapTTToResponse(TTModel obj) {
        return TTResponse.builder()
                .trainId(obj.getTrainId())
                .date(obj.getDate())
                .trainPathName(obj.getTrainPathName())
                .capacity(obj.getCapacity())
                .build();
    }

    public void addTT(TTModel obj) {
        repository.save(obj);
    }

    public String deleteTT(String trainId) {
        repository.deleteByTrainId(trainId);
        return "DELETED :D";
    }

    public List<TTModel> filterTT(FilterRequest obj){
        return repository.findByDateAndTrainPathName(obj.getFilterDate(), obj.getFilterPathName());
    }

    public void updateCapacity(UpdateCapacity obj){
        TTModel updateThis = repository.findById(obj.getTimetableId()).get();
        updateThis.setCapacity(updateThis.getCapacity() - obj.getSubtractThisCapacityFromTheModel());
        repository.save(updateThis);
    }

    public int getCapacityById(String id){
        System.out.println("\n\nLOLOLOL\n\n\n\nBHAHDHDHAHD\n\n\nLOL YE DEKHO: "+ repository.findById(id).get().getCapacity());
        return repository.findById(id).get().getCapacity();
    }
}
