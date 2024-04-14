package com.trainapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainapp.model.TrainDetails;

public interface TrainRepository extends MongoRepository<TrainDetails, String>{


}
