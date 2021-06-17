package com.qa.hobby.dto;

import java.util.List;

public class TeamDTO {

	private Long TeamId;

	private String TeamName;

	private List<PlayerDTO> players;
	
	public TeamDTO() {
		super();
	}
	

	public TeamDTO(String teamName, List<PlayerDTO> players) {
		super();
		TeamName = teamName;
		this.players = players;
	}


	public TeamDTO(String teamName) {
		super();
		TeamName = teamName;
		
	}


	public TeamDTO(Long teamId, String teamName) {
		super();
		TeamId = teamId;
		TeamName = teamName;
		
	}

	public TeamDTO(Long teamId, String teamName, List<PlayerDTO> players) {
		super();
		TeamId = teamId;
		TeamName = teamName;
		this.players = players;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TeamId == null) ? 0 : TeamId.hashCode());
		result = prime * result + ((TeamName == null) ? 0 : TeamName.hashCode());
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamDTO other = (TeamDTO) obj;
		if (TeamId == null) {
			if (other.TeamId != null)
				return false;
		} else if (!TeamId.equals(other.TeamId))
			return false;
		if (TeamName == null) {
			if (other.TeamName != null)
				return false;
		} else if (!TeamName.equals(other.TeamName))
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		return true;
	}

	
	
	

//	@Override
//	public boolean equals(Object obj) {
//		TeamDTO o = null;
//		if (obj instanceof TeamDTO)
//			o = (TeamDTO) obj;
//		else
//			return false;
//
//		if (this.TeamId == o.getTeamId() && this.TeamName.equals(o.getTeamName()))
//			return true;
//
//		return false;
//	}
	
	

}
