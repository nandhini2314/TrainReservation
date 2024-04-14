package com.trainapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainapp.model.ReservationDetails;

public interface ReservationRepository extends  MongoRepository<ReservationDetails,String> {

}
