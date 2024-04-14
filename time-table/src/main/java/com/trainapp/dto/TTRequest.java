package com.trainapp.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TTRequest {
    private String trainId;
    private String trainPathName;
    private String origin;
    private String destination;
    private List<String> passengerName;
    private Date date;
    private int capacity;
}
