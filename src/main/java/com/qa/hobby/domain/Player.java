package com.qa.hobby.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long PlayerId;
	
	String PlayerName;
	
	int age;

	@ManyToOne
	private Team team;

	public Player() {
		super();
	}

	public Player(Long playerId, String playerName, int age) {
		super();
		PlayerId = playerId;
		PlayerName = playerName;
		this.age = age;
	}

	public Player(String name, int age) {
		super();
		PlayerName = name;
		this.age = age;
	}

	public Long getPlayerId() {
		return PlayerId;
	}

	public void setPlayerId(Long playerId) {
		PlayerId = playerId;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String name) {
		PlayerName = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Player [PlayerId=" + PlayerId + ", Name=" + PlayerName + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PlayerName == null) ? 0 : PlayerName.hashCode());
		result = prime * result + ((PlayerId == null) ? 0 : PlayerId.hashCode());
		result = prime * result + age;
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
		Player other = (Player) obj;
		if (PlayerName == null) {
			if (other.PlayerName != null)
				return false;
		} else if (!PlayerName.equals(other.PlayerName))
			return false;
		if (PlayerId == null) {
			if (other.PlayerId != null)
				return false;
		} else if (!PlayerId.equals(other.PlayerId))
			return false;
		if (age != other.age)
			return false;
		return true;
	}

}
