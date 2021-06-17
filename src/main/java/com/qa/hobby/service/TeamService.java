package com.qa.hobby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.TeamDTO;
import com.qa.hobby.repo.TeamRepo;
import com.qa.hobby.utils.TeamMapper;

@Service
public class TeamService {

	private TeamRepo repo;
	public TeamMapper mapper;

	public TeamService(TeamRepo repo, TeamMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public TeamDTO createTeam(Team team) {
		Team saved = this.repo.save(team);
		return this.mapper.mapToDTO(saved);
	}

//	public TeamDTO findTeam(Long id) {
//		Optional<Team> optionalGarage = this.repo.findById(id);
//		Team found = optionalGarage.orElseThrow(() -> new EntityNotFoundException());
//		return this.mapper.mapToDTO(found);
//	}

	public TeamDTO updateTeam(Long id, Team newData) {
		Team existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); // fetch existing
																									// from db

		existing.setTeamId(id);
		existing.setTeamName(newData.getTeamName()); // update the values

		Team updated = this.repo.save(existing); // save it back to overwrite original

		return this.mapper.mapToDTO(updated);
	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public List<TeamDTO> getTeam() {
		List<Team> team = this.repo.findAll();

		List<TeamDTO> dtos = new ArrayList<>();

		for (Team t : team) {
			TeamDTO dto = this.mapper.mapToDTO(t);
			dtos.add(dto);
		}
		return dtos;
	}

}