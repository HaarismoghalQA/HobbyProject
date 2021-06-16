package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.hobby.domain.Player;
import com.qa.hobby.repo.PlayerRepo;
import com.qa.hobby.service.PlayerService;



@SpringBootTest
public class PlayerServiceTest {
	
	@Autowired
	private PlayerService service;
	
	@MockBean
	private PlayerRepo repo;
	
	@Test
	void testAddPlayer() { 
		//GIVEN
		Long testId = 1l;
		Player testData = new Player("John", 23);
		
		Mockito.when(this.repo.save(testData)).thenReturn(testData);
		assertThat(this.service.addPlayer(testData)).isEqualTo(testData);
	}
	
	
//	@Test
//	void testGetAll() {
//			
//		Player testPerson = new Player(1l, "Haaris", 23);
//		List<Player> testPersons = List.of(testPerson);
//		
//		Mockito.when(this.repo.save(testPerson)).thenReturn(testPerson);
//		Mockito.when(this.repo.findAll()).thenReturn(testPersons);
//		
//		assertThat(this.service.getPlayer()).isEqualTo(testPersons);
//	}
//	
//	
//	@Test
//	void testUpdate() {
//		// GIVEN
//		Long testId = 1l;
//		Player testData = new Player("John", 23);
//		Player existing = new Player(1l, null, 0);
//		Player updatedPerson = new Player(testId, "John", 23);
//
//		// When
//		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
//		Mockito.when(this.repo.save(updatedPerson)).thenReturn(updatedPerson);
//
//		//Then
//		assertThat(this.service.updatePlayer(testId, updatedPerson)).isEqualTo(updatedPerson);
//		
//		Mockito.verify(this.repo,Mockito.times(1)).findById(testId);
//		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPerson);
//	}
//
//	@Test
//	void testDelete() {
//		// GIVEN
//		Long testId = 1l;
//		boolean exists = false;
//
//		// When
//		Mockito.when(this.repo.existsById(testId)).thenReturn(false);
//
//		// Then
//		assertThat(this.service.removePlayer(testId)).isEqualTo(!exists);
//	
//		
//		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
//	}
}
