package com.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainapp.dto.TrainRequest;
import com.trainapp.dto.TrainResponse;
import com.trainapp.model.TrainDetails;
import com.trainapp.repository.TrainRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainService {

    @Autowired
    private TrainRepository repository;

    @SuppressWarnings("null")
    public void createTrain(TrainRequest train) {
        TrainDetails trainSave = TrainDetails.builder()
                .trainId(train.getTrainId())
                .trainName(train.getTrainName())
                .trainType(train.getTrainType())
                .trainImage(train.getTrainImage())
                .trainRoute(train.getTrainRoute())
                .trainFare(train.getTrainFare())
                .trainCapacity(train.getTrainCapacity())
                .build();

        repository.save(trainSave);
        log.info(trainSave.getTrainId() + " has been added");
    }

    @SuppressWarnings("null")
    public void updateTrain(TrainRequest train) {
        TrainDetails trainUpdate = TrainDetails.builder()
                .trainId(train.getTrainId())
                .trainName(train.getTrainName())
                .trainType(train.getTrainType())
                .trainImage(train.getTrainImage())
                .trainRoute(train.getTrainRoute())
                .trainFare(train.getTrainFare())
                .trainCapacity(train.getTrainCapacity())
                .build();
        repository.save(trainUpdate);
        log.info(trainUpdate.getTrainId() + " has been updated");
    }

    @SuppressWarnings("null")
    public TrainResponse getSpecificTrain(String trainId) {
        TrainDetails train = repository.findById(trainId).get();
        return mapToTrainResponse(train);
    }

    @SuppressWarnings("null")
    public void deleteTrain(String trainId) {
        repository.deleteById(trainId);
        log.info(trainId + " has been updated");
    }

    public List<TrainResponse> getAllTrains() {
        List<TrainDetails> trains = repository.findAll();
        return trains.stream().map(this::mapToTrainResponse).toList();

    }

    private TrainResponse mapToTrainResponse(TrainDetails train) {
        return TrainResponse.builder()
                .trainId(train.getTrainId())
                .trainName(train.getTrainName())
                .trainType(train.getTrainType())
                .trainImage(train.getTrainImage())
                .trainRoute(train.getTrainRoute())
                .trainFare(train.getTrainFare())
                .trainCapacity(train.getTrainCapacity())
                .build();
    }

    public int getCapacity(String Id){
        return repository.findById(Id).get().getTrainCapacity();
    }
}
