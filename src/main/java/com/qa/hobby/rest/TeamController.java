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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.TeamDTO;
import com.qa.hobby.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

	private TeamService service;

	@Autowired
	public TeamController(TeamService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/create")
	public TeamDTO createTeam(@RequestBody Team teams) {
		return this.service.createTeam(teams);
	}

	@GetMapping("/find/{id}")
	public TeamDTO find(@PathVariable Long id) {
		return this.service.findTeam(id);
	}

	@GetMapping("/getTeam")
	public List<TeamDTO> getTeam() {
		return this.service.getTeam();
	}

	@PutMapping("/update/{id}")
	public TeamDTO updateTeam(@RequestBody Team team, @PathVariable Long id) {
		return this.service.updateTeam(id, team);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}

}
