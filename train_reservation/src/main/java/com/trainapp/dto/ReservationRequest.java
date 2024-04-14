package com.trainapp.dto;

import java.util.List;
import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private String trainId;
    private String trainPathName;
    private String timetableId;
    private String origin;
    private String destination;
    private List<String> passengerName;
}
