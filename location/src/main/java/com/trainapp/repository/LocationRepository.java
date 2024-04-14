package com.trainapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainapp.model.LocationModel;

public interface LocationRepository extends MongoRepository<LocationModel, String> {
    LocationModel findByName(String name);
}
