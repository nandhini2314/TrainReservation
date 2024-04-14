package com.trainapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private String trainId;
    private String trainPathName;
    private String origin;
    private String destination;
    private List<String> passengerName;
}
