package com.qa.hobby.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.hobby.domain.Player;
import com.qa.hobby.repo.PlayerRepo;




@Service
public class PlayerService {

	private PlayerRepo repo;
	
	public PlayerService(PlayerRepo repo) {
        super();
        this.repo = repo;
    }

	public Player addPlayer(Player player) {
		return this.repo.save(player);
	}

	public List<Player> getAllPlayer() {
		return this.repo.findAll();
	}
	
	public Player updatePlayer(Long id, Player player) {
        Optional<Player> existingOptional = this.repo.findById(id); 
        Player existing = existingOptional.get();

        existing.setAge(player.getAge());
        existing.setPlayerName(player.getPlayerName()); 

        return this.repo.save(existing);
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
