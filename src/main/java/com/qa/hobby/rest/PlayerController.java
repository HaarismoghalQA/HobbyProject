package com.qa.hobby.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.domain.Player;
import com.qa.hobby.service.PlayerService;

@RestController
public class PlayerController {
	
	private PlayerService service;

	public PlayerController(PlayerService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
    public Player addPlayer(@RequestBody Player player) {
        return this.service.addPlayer(player);
    }
	
	 @GetMapping("/getAll")
	    public List<Player> getAllPlayer() {
	        return this.service.getAllPlayer();
	    }

	    @PutMapping("/update/{id}")
	    public Player updatePlayer(@PathVariable("id") Long id, @RequestBody Player player) {
	    	return this.service.updatePlayer(id, player);
	    }

	    @DeleteMapping("/delete/{id}")
	    public boolean removePlayer(@PathVariable Long id) {
	        // Remove Person and return it
	    	return this.service.removePlayer(id);
	    }

}
