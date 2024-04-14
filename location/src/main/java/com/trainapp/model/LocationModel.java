package com.trainapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="location")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel {
@Id
private String id;
private List<String> stationList;
private String name;
}
