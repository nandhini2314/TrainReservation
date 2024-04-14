package com.trainapp.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(value = "reservation_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationDetails {
    @Id
    private String id;
    private String timetableId;
    private String origin;
    private String destination;
    private String trainId;
    private String trainPathName;
    private float totalFare;
    private List<String> passengerName;
}