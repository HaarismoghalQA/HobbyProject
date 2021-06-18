package com.qa.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobby.HobbyProjectApplication;
import com.qa.hobby.domain.Player;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.repo.PlayerRepo;
import com.qa.hobby.service.PlayerService;

@SpringBootTest(classes = HobbyProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PlayerSerivceUnitTest {
 
	@Autowired
	private PlayerService service;

	@MockBean
	private PlayerRepo repo;

	@Test
	void testAddPerson() {
		// GIVEN
		Long testId = 1l;
		Player testData = new Player(testId, "John", 23);
		PlayerDTO playerDTO = this.service.mapper.mapToDTO(testData);

		Mockito.when(this.repo.save(testData)).thenReturn(testData); 
		assertThat(this.service.addPlayer(testData)).isEqualTo(playerDTO);
	}

	@Test
	void testGetAll() {

		Player testPlayer = new Player(1l, "Haaris", 23);
		List<Player> testPlayers = List.of(testPlayer);
		List<PlayerDTO> equalsTestPlayers = List.of(this.service.mapper.mapToDTO(testPlayer));

		Mockito.when(this.repo.save(testPlayer)).thenReturn(testPlayer);
		Mockito.when(this.repo.findAll()).thenReturn(testPlayers);

		assertThat(this.service.getPlayer()).isEqualTo(equalsTestPlayers);
	}

	@Test
	void testUpdate() {
		// GIVEN
		Long testId = 1l;
		Player existing = new Player(1l, null, 0);
		Player updatedPerson = new Player(testId, "John", 23);

		// When
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updatedPerson)).thenReturn(updatedPerson);

		// Then
		assertThat(this.service.updatePlayer(testId, updatedPerson))
				.isEqualTo(this.service.mapper.mapToDTO(updatedPerson));

		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPerson);
	}

	@Test
	void testDelete() {
		// GIVEN
		Long testId = 1l;
		boolean exists = false;

		// When
		Mockito.when(this.repo.existsById(testId)).thenReturn(false);

		// Then
		assertThat(this.service.removePlayer(testId)).isEqualTo(!exists);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}
}
