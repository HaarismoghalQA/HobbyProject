package com.qa.hobby.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.domain.Player;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	private PlayerService service;

	@Autowired
	public PlayerController(PlayerService service) {
		super();
		this.service = service;
	}


	@PostMapping("/create")
	public PlayerDTO addPlayer(@RequestBody Player player) {
		return this.service.addPlayer(player);
	}

	@GetMapping("/getAll")
	public List<PlayerDTO> getPlayer() {
		return this.service.getPlayer();
	}

	@PutMapping("/update/{playerId}")
	public PlayerDTO updatePlayer(@PathVariable Long playerId, @RequestBody Player player) {
		return this.service.updatePlayer(playerId, player);
	}

	@DeleteMapping("/delete/{playerId}")
	public boolean removePlayer(@PathVariable Long playerId) {
		// Remove Person and return it
		return this.service.removePlayer(playerId);
	}

}
