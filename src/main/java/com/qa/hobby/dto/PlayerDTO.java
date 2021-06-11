package com.qa.hobby.dto;

public class PlayerDTO {

	Long PlayerId;
	String PlayerName;
	int age;

	public PlayerDTO() {
		
	}

	public PlayerDTO(String name, int age) {
		super();
		PlayerName = name;
		this.age = age;
	}

	public PlayerDTO(Long playerId, String name, int age) {
		super();
		PlayerId = playerId;
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

}