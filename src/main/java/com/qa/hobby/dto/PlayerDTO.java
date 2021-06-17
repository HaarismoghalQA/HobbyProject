package com.qa.hobby.dto;

public class PlayerDTO {

	Long PlayerId;
	String PlayerName;
	int age;

	public PlayerDTO() {

	}

	//Commented to see if it breaks my program
	
//	public PlayerDTO(String name, int age) {
//		super();
//		PlayerName = name;
//		this.age = age;
//	}

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


//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((PlayerId == null) ? 0 : PlayerId.hashCode());
//		result = prime * result + ((PlayerName == null) ? 0 : PlayerName.hashCode());
//		result = prime * result + age;
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
		PlayerDTO other = (PlayerDTO) obj;
		if (PlayerId == null) {
			if (other.PlayerId != null)
				return false;
		} else if (!PlayerId.equals(other.PlayerId))
			return false;
		if (PlayerName == null) {
			if (other.PlayerName != null)
				return false;
		} else if (!PlayerName.equals(other.PlayerName))
			return false;
		if (age != other.age)
			return false;
		return true;
	}

	
	
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		PlayerDTO o = null;
//		if (obj instanceof PlayerDTO)
//			o = (PlayerDTO) obj;
//		else
//			return false;
//
//		if (this.PlayerId == o.getPlayerId() && this.age == o.getAge() && this.PlayerName.equals(o.getPlayerName()))
//			return true;
//
//		return false;
//	}

}