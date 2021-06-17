package com.qa.hobby.dto;

import java.util.List;

public class TeamDTO {

	private Long TeamId;

	private String TeamName;

	private List<PlayerDTO> players;

	public Long getTeamId() {
		return TeamId;
	}

	public void setTeamId(Long id) {
		this.TeamId = id;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String TeamName) {
		this.TeamName = TeamName;
	}

	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> player) {
		this.players = player;
	}

	@Override
	public boolean equals(Object obj) {
		TeamDTO o = null;
		if (obj instanceof TeamDTO)
			o = (TeamDTO) obj;
		else
			return false;

		if (this.TeamId == o.getTeamId() && this.TeamName.equals(o.getTeamName()))
			return true;

		return false;
	}

}
