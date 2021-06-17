package com.qa.hobby.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long TeamId;

	String TeamName;

	@OneToMany(mappedBy = "team")
	@JsonIgnore
	List<Player> players = new ArrayList<>();
	
	public Team() {
		super();
	}
	
	
	//Commented it out to see if it breaks my website
	
//	public Team(String teamName, List<Player> players) {
//		super();
//		TeamName = teamName;
//		this.players = players;
//	}



	public Team(String teamName) {
		super();
		TeamName = teamName;
	}



	public Team(Long teamId, String teamName) {
		super();
		TeamId = teamId;
		TeamName = teamName;
	}

//	public Team(Long teamId, String teamName, List<Player> players) {
//		super();
//		TeamId = teamId;
//		TeamName = teamName;
//		this.players = players;
//	}

	public Long getTeamId() {
		return TeamId;
	}

	public void setTeamId(Long teamId) {
		TeamId = teamId;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> player) {
		players = player;
	}


	//Commented it out to see if it breaks my website
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((TeamId == null) ? 0 : TeamId.hashCode());
//		result = prime * result + ((TeamName == null) ? 0 : TeamName.hashCode());
//		result = prime * result + ((players == null) ? 0 : players.hashCode());
//		return result;
//	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
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
	
	

}
