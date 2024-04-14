package com.trainapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.sql.Date;
import java.util.List;
import com.trainapp.model.TTModel;

public interface TTRepo extends MongoRepository<TTModel, String> {

    List<TTModel> findByTrainId(String trainId);

    void deleteByTrainId(String trainId);

    TTModel findByTrainIdAndDate(String trainId, Date date);

    List<TTModel> findByDateAndTrainPathName(Date filterDate, String filterPathName);
}
