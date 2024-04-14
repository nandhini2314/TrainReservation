package com.trainapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TTResponse {
    private String trainId;
    private Date date;
    private String trainPathName;
    private int capacity;
}
