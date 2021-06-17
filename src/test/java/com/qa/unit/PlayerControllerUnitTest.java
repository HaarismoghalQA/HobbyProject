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
import com.qa.hobby.domain.Player;
import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.rest.PlayerController;
import com.qa.hobby.service.PlayerService;



@SpringBootTest(classes = HobbyProjectApplication.class)
@ActiveProfiles("test")
public class PlayerControllerUnitTest {

	@Autowired
	private PlayerController controller;

	@MockBean
	private PlayerService service;

	@Test
	void testCreatePlayer() {
		// GIVEN
		Player testData = new Player(1l, "Ronaldo", 23);
		PlayerDTO newDTO = new PlayerDTO(1l, "Ronaldo", 23);

		// WHEN
		Mockito.when(service.addPlayer(testData)).thenReturn(newDTO);

		// THEN
		assertThat(this.controller.addPlayer(testData)).isEqualTo(newDTO);

		Mockito.verify(this.service, Mockito.times(1)).addPlayer(testData);
	}

	@Test
	void testGetPlayer() {
		// GIVEN


		PlayerDTO existingDTO = new PlayerDTO(1l, "Ronaldo", 23);
		List<PlayerDTO> dtos = List.of(existingDTO);

		// WHEN
		Mockito.when(this.service.getPlayer()).thenReturn(dtos);

		// THEN
		assertThat(this.controller.getPlayer()).isEqualTo(dtos);
		
		Mockito.verify(this.service, Mockito.times(1)).getPlayer();

	}


	@Test
	void testUpdatePlayer() {
		// GIVEN
		long testId = 1;
		Player testData = new Player(1l, "Ronaldo", 23);
		Player updatedGame = new Player(testId, "Ronaldo", 23);
		PlayerDTO updatedGameDTO = new PlayerDTO(testId, "Ronaldo", 23);

		// WHEN
		Mockito.when(this.service.updatePlayer(testId, testData)).thenReturn(updatedGameDTO);

		// THEN
		assertThat(this.controller.updatePlayer(testId,testData)).isEqualTo(updatedGameDTO);

		Mockito.verify(this.service, Mockito.times(1)).updatePlayer(testId, updatedGame);
	}

	@Test
	void testDelete() {
		// GIVEN
		long testId = 1;
		// WHEN
		Mockito.when(this.service.removePlayer(testId)).thenReturn(true);

		// THEN
		assertThat(this.controller.removePlayer(testId)).isEqualTo(true);

		Mockito.verify(this.service, Mockito.times(1)).removePlayer(testId);
	}


}