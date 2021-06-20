package com.qa.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.qa.hobby.dto.PlayerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.HobbyProjectApplication;
import com.qa.hobby.domain.Player;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.repo.PlayerRepo;
import com.qa.hobby.service.PlayerService;




@SpringBootTest(classes = HobbyProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("test")
public class playerIntegration {

	@Autowired
	private MockMvc mvc; // allows us to send mock requests
	

	@Autowired
	private PlayerRepo repo;

	@Autowired
	private ObjectMapper mapper; // ObjectMapper provides functionality for reading and writing JSON

	@Test
	void testCreate() throws Exception {		
		 
		Player testPlayer = new Player("Haaris", 23);
		String testPlayerAsJSON = this.mapper.writeValueAsString(testPlayer); 	 
		
//		Player testSavedPlayer = new Player("Haaris", 23);
//		String testSavedPlayerAsJSON = this.mapper.writeValueAsString(testSavedPlayer);

		
		
		PlayerDTO savedPlayereDTO = new PlayerDTO(2l, "Haaris", 23);
		String savedPlayerAsJSON = this.mapper.writeValueAsString(savedPlayereDTO);
		
	

		RequestBuilder mockRequest = post("/player/create").content(testPlayerAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(savedPlayerAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
		
		
	}
	
	@Test
	void testGetAll() throws Exception {
		PlayerDTO testPlayer = new PlayerDTO(1l, "Haaris", 23);
		List<PlayerDTO> testPlayers = List.of(testPlayer);
		String testPlayerAsJSONArray = this.mapper.writeValueAsString(testPlayers);

		this.mvc.perform(get("/player/getAll")).andExpect(status().isOk()).andExpect(content().json(testPlayerAsJSONArray));
	}
	
	@Test
	void testUpdate() throws Exception {
		Player testPlayer = new Player (1l, "Haaris", 23);
		String testPlayerAsJSON = this.mapper.writeValueAsString(testPlayer);
		
		PlayerDTO savedPlayereDTO = new PlayerDTO(1l, "Haaris", 23);
		String savedPlayerAsJSON = this.mapper.writeValueAsString(savedPlayereDTO);
		
		

		this.mvc.perform(put("/player/update/1").content(testPlayerAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(savedPlayerAsJSON));
	}	 
	
	@Test
	void testDelete() throws Exception {
		assertThat(repo.existsById(1l));
		this.mvc.perform(delete("/player/delete/1")).andExpect(status().isOk());
		assertThat(!(repo.existsById(1l)));		
	}
}
