package com.qa.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobby.HobbyProjectApplication;
import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.TeamDTO;
import com.qa.hobby.rest.TeamController;
import com.qa.hobby.service.TeamService;

@SpringBootTest(classes = HobbyProjectApplication.class)
@ActiveProfiles("test")
public class TestControllerUnitTest {

	@Autowired
	private TeamController controller;

	@MockBean
	private TeamService service;

	@Test
	void testCreateTeam() {
		// GIVEN
		Team testData = new Team("Arsenal");
		TeamDTO createdDTO = new TeamDTO(1l, "Arsenal");

		// WHEN
		Mockito.when(service.createTeam(testData)).thenReturn(createdDTO);

		// THEN
		assertThat(this.controller.createTeam(testData)).isEqualTo(createdDTO);

		Mockito.verify(this.service, Mockito.times(1)).createTeam(testData);
		
		
		
	}

	@Test
	void testGetTeam() {
		// GIVEN	
		TeamDTO createdDTO = new TeamDTO(1l, "Arsenal");
		List<TeamDTO> dtos = List.of(createdDTO);

		// WHEN
		Mockito.when(this.service.getTeam()).thenReturn(dtos);

		// THEN
		assertThat(this.controller.getTeam()).isEqualTo(dtos);
		
		Mockito.verify(this.service, Mockito.times(1)).getTeam();

	}


	@Test
	void testUpdatePlatform() {
		// GIVEN
		long testId = 1;
		Team testData = new Team(1l, "Arsenal");
		Team updatedPlatform = new Team(testId, "Arsenal");
		TeamDTO updatedPlatformDTO = new TeamDTO(testId, "Arsenal");

		// WHEN
		Mockito.when(this.service.updateTeam(testId, testData)).thenReturn(updatedPlatformDTO);

		// THEN
		assertThat(this.controller.updateTeam(testData, testId)).isEqualTo(updatedPlatformDTO);

		Mockito.verify(this.service, Mockito.times(1)).updateTeam(testId, updatedPlatform);
	}

	@Test
	void testDelete() {
		// GIVEN
		long testId = 1;
		// WHEN
		Mockito.when(this.service.delete(testId)).thenReturn(true);

		// THEN
		assertThat(this.controller.delete(testId)).isEqualTo(true);

		Mockito.verify(this.service, Mockito.times(1)).delete(testId);
	}

}