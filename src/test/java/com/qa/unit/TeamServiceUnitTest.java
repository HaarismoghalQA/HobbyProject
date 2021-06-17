package com.qa.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.hobby.domain.Player;
import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.TeamDTO;
import com.qa.hobby.repo.TeamRepo;
import com.qa.hobby.service.TeamService;

@SpringBootTest
public class TeamServiceUnitTest {

	@Autowired
	private TeamService service;

	@MockBean
	private TeamRepo repo;

	@Test
	void testAddTeam() {
		// GIVEN
		Long testId = 1l;
		Team testData = new Team(testId, "Liverpool");
		TeamDTO teamDTO = this.service.mapper.mapToDTO(testData);

		Mockito.when(this.repo.save(testData)).thenReturn(testData);
		assertThat(this.service.createTeam(testData)).isEqualTo(teamDTO);
	}

	@Test
	void testGetAll() {

		Team testTeam = new Team(1l, "Liverpool");
		List<Team> testTeams = List.of(testTeam);
		List<TeamDTO> equalsTeams = List.of(this.service.mapper.mapToDTO(testTeam));

		Mockito.when(this.repo.save(testTeam)).thenReturn(testTeam);
		Mockito.when(this.repo.findAll()).thenReturn(testTeams);

		assertThat(this.service.getTeam()).isEqualTo(equalsTeams);
	}

	@Test
	void testUpdate() {
		// GIVEN
		Long testId = 1l;
		Team existing = new Team(testId, "");
		Team updatedTeam = new Team(testId, "Liverpool");
		
		// When
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updatedTeam)).thenReturn(updatedTeam);

		// Then
		assertThat(this.service.updateTeam(testId, updatedTeam)).isEqualTo(this.service.mapper.mapToDTO(updatedTeam));

		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedTeam);
	}

	@Test
	void testDelete() {
		// GIVEN
		Long testId = 1l;
		boolean exists = false;

		// When
		Mockito.when(this.repo.existsById(testId)).thenReturn(false);

		// Then
		assertThat(this.service.delete(testId)).isEqualTo(!exists);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}
}
