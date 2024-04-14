package com.trainapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "train_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrainDetails {
    @Id
    @Indexed(unique = true)
    private String trainId;
    private String trainName;
    private String trainType;
    private String trainImage;
    private int trainRoute;
    private int trainFare;
    private int trainCapacity;
}
