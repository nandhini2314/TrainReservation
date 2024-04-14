package com.trainapp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "timetable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TTModel {
    @Id
    private String id;
    private String trainId;
    private String trainPathName;
    private Date date;
    private int capacity;
}
