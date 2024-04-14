package com.trainapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainapp.dto.TrainRequest;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc

class TrainDetailsApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("null")
	@Test
	void shouldCreateTrain() {
		TrainRequest request = getTrainRequest();
		try {
			String request_string = objectMapper.writeValueAsString(request);
			mockMvc.perform(MockMvcRequestBuilders.post("/train/add_train")
					.contentType(MediaType.APPLICATION_JSON).content(request_string)).andExpect(status().isCreated());
		} catch (Exception e) {
			System.out.println("Okay");
		}
		;
	}

	private TrainRequest getTrainRequest() {
		return TrainRequest.builder().trainName("Test_Train").build();
	}
}
