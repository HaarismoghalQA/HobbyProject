package com.qa.hobby.dto;

import java.util.List;

public class TeamDTO {

	private Long Teamid;

	private String TeamName;

	private List<PlayerDTO> players;

	public Long getTeamId() {
		return Teamid;
	}

	public void setTeamId(Long id) {
		this.Teamid = id;
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

}
