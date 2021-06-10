package com.qa.hobby.domain;

public class Team {
	
	Long TeamId;
	String TeamName;

	
	public Team(Long teamId, String teamName) {
		super();
		TeamId = teamId;
		TeamName = teamName;
	}
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
	

}
