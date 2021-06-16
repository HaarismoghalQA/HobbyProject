package com.qa.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.domain.Player;
import com.qa.hobby.repo.PlayerRepo;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // prevents port conflicts
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:player-schema.sql",
		"classpath:player-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class playerIntegration {

	@Autowired
	private MockMvc mvc; // allows us to send mock requests

	@Autowired
	private PlayerRepo repo;

	@Autowired
	private ObjectMapper mapper; // ObjectMapper provides functionality for reading and writing JSON

	@Test
	void testCreate() throws Exception {
		Player testPlayer = new Player(1l, "Haaris", 23);
		String testPlayerAsJSON = this.mapper.writeValueAsString(testPlayer);

		Player testSavedPlayer = new Player(1l, "Haaris", 23);
		// testSavedPerson.setId(2);
		String testSavedPersonAsJSON = this.mapper.writeValueAsString(testSavedPlayer);

		RequestBuilder mockRequest = post("/player/create").content(testPlayerAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(testSavedPersonAsJSON);

		// comparing the sent JSON vs received JSON
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);

		// if things goes wrong run detailed comparison logging the response
//		MvcResult results = this.mvc.perform(mockRequest).andExpect(checkStatus).andReturn();
//
//		String responseBody = results.getResponse().getContentAsString();
//
//		Player responseData = this.mapper.readValue(responseBody, Player.class);
//		System.out.println("Person: " + responseData);

	//	assertThat(responseData).isEqualTo(testSavedPlayer);
	}
}
