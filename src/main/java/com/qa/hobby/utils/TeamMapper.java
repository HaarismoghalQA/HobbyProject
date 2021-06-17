package com.qa.hobby.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.hobby.domain.Player;
import com.qa.hobby.domain.Team;
import com.qa.hobby.dto.PlayerDTO;
import com.qa.hobby.dto.TeamDTO;

@Service
public class TeamMapper implements Mapper<Team, TeamDTO> {

	private PlayerMapper playerMapper;

	public TeamMapper(PlayerMapper playerMapper) {
		super();
		this.playerMapper = playerMapper;
	}

	@Override
	public TeamDTO mapToDTO(Team entity) {
		TeamDTO dto = new TeamDTO();
		if (entity == null)
			return null;

		dto.setTeamId(entity.getTeamId());
		dto.setTeamName(entity.getTeamName());
		List<PlayerDTO> players = new ArrayList<>();
		for (Player player : entity.getPlayers()) {
			players.add(this.playerMapper.mapToDTO(player));
		}
		dto.setPlayers(players);
		return dto;
	}

	@Override
	public Team mapFromDTO(TeamDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}