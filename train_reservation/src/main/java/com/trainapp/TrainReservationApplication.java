package com.trainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrainReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainReservationApplication.class, args);
	}

}
