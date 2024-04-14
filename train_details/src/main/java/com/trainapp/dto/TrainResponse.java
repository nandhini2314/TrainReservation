package com.trainapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainResponse {
    private String trainId;
    private String trainName;
    private String trainType;
    private String trainImage;
    private int trainRoute;
    private int trainFare;
    private int trainCapacity;

    public String getTrainId() {
        return this.trainId;
    }

    public String getTrainName() {
        return this.trainName;
    }

    public String getTrainType() {
        return this.trainType;
    }

    public String getTrainImage() {
        return this.trainImage;
    }

    public int getTrainRoute() {
        return this.trainRoute;
    }

    public int getTrainFare() {
        return this.trainFare;
    }

    public int getTrainCapacity() {
        return this.trainCapacity;
    }
}