package com.qa.hobby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.hobby.domain.Player;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.repo.PlayerRepo;
import com.qa.hobby.utils.PlayerMapper;

@Service
public class PlayerService {

	private PlayerRepo repo;
	public PlayerMapper mapper;

	public PlayerService(PlayerRepo repo, PlayerMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public PlayerDTO addPlayer(Player player) {
		Player saved = this.repo.save(player);
		return this.mapper.mapToDTO(saved);
	}

	// public PlayerDTO getAllPlayer(Long id) {
		//	Optional<Player> optionalPlayer = this.repo.findById(id);
		//	Player found = optionalPlayer.orElseThrow(() -> new EntityNotFoundException());
		//	return this.mapper.mapToDTO(found);
		// return this.repo.findAll();
		// return this.service.getAllPlayer();
	//}

	public List<PlayerDTO> getPlayer() {
		List<Player> players = this.repo.findAll();
		List<PlayerDTO> dtos = new ArrayList<>();

		PlayerDTO dto = null;
		for (Player player : players) {
			dto = this.mapper.mapToDTO(player);
			dtos.add(dto);
		}

		return dtos;
	}

	public PlayerDTO updatePlayer(Long id, Player player) {
		Player existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); // fetch existing
																									// from
		// db
		System.out.println("Player Id: "+id);
		existing.setPlayerId(id);
		existing.setAge(player.getAge()); // update the values
		existing.setPlayerName(player.getPlayerName());

		Player updated = this.repo.save(existing); // save it back to overwrite original

		return this.mapper.mapToDTO(updated);

//        Optional<Player> existingOptional = this.repo.findById(id); 
//        Player existing = existingOptional.get();
//
//        existing.setAge(player.getAge());
//        existing.setPlayerName(player.getPlayerName()); 
//
//        return this.repo.save(existing);
	}

	public boolean removePlayer(Long id) {
		// removes the entity
		this.repo.deleteById(id);
		// checks to see if it still exists
		boolean exists = this.repo.existsById(id);
		// returns true if entity no longer exists
		return !exists;
	}

}
