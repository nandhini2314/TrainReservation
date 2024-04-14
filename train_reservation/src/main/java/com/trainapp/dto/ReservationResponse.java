package com.trainapp.dto;

import java.util.List;
import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private String trainId;
    private String trainRouteName;
    private String origin;
    private String destination;
    private List<String> passengerName;
    // which will come form passengerName.length
    private int quantity;
    // which will come form quantity and train fare
    private float fare;
}
