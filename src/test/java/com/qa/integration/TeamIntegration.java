package com.qa.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.qa.hobby.dto.TeamDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.HobbyProjectApplication;
import com.qa.hobby.domain.Player;
import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.repo.PlayerRepo;
import com.qa.hobby.repo.TeamRepo;
import com.qa.hobby.service.PlayerService;

@SpringBootTest(classes = HobbyProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class TeamIntegration {

	@Autowired
	private MockMvc mvc; // allows us to send mock requests

	@Autowired
	private TeamRepo repo;

	@Autowired
	private ObjectMapper mapper; // ObjectMapper provides functionality for reading and writing JSON

	@Test
	void testCreate() throws Exception {

		Team testTeam = new Team("Arsenal");
		String testTeamAsJSON = this.mapper.writeValueAsString(testTeam);

		Team testSavedTeam = new Team(2l, "Arsenal");
		String testSavedTeamAsJSON = this.mapper.writeValueAsString(testSavedTeam); 

		RequestBuilder mockRequest = post("/team/create").content(testTeamAsJSON)
				.contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(testSavedTeamAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetAll() throws Exception {

		PlayerDTO player = new PlayerDTO(1l, "Haaris", 23);
		List<PlayerDTO> playerDTOs = List.of(player);

		TeamDTO testTeam = new TeamDTO(1l, "Arsenal", playerDTOs);
		List<TeamDTO> testTeams = List.of(testTeam);

		String testTeamsAsJSONArray = this.mapper.writeValueAsString(testTeams);

		this.mvc.perform(get("/team/getTeam")).andExpect(status().isOk())
				.andExpect(content().json(testTeamsAsJSONArray));
	}

	@Test
	void testUpdateTeam() throws Exception {
		Team testPlatform = new Team(1l, "Arsenal");
		String testPlatformAsJSON = this.mapper.writeValueAsString(testPlatform);

		PlayerDTO player = new PlayerDTO(1l, "Haaris", 23);
		List<PlayerDTO> PlayersDTO = List.of(player);

		TeamDTO updatedTeamDTO = new TeamDTO(1l, "Arsenal", PlayersDTO);
		String updatedTeamAsJSON = this.mapper.writeValueAsString(updatedTeamDTO);

		this.mvc.perform(put("/team/update/1").content(testPlatformAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(updatedTeamAsJSON));
	}

	@Test
	void testDelete() throws Exception {
		assertThat(repo.existsById(1l));
		this.mvc.perform(delete("/team/remove/1")).andExpect(status().isOk());
		assertThat(!(repo.existsById(1l)));
	}
}
