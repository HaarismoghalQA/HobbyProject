package com.qa.hobby.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long PlayerId;
	@Column(nullable = false)
	String Name;
	@Column(nullable = false)
	int age;

	public Player() {
		super();
	}

	public Player(Long playerId, String name, int age) {
		super();
		PlayerId = playerId;
		Name = name;
		this.age = age;
	}

	public Player(String name, int age) {
		super();
		Name = name;
		this.age = age;
	}

	public Long getPlayerId() {
		return PlayerId;
	}

	public void setPlayerId(Long playerId) {
		PlayerId = playerId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

	@Override
	public String toString() {
		return "Player [PlayerId=" + PlayerId + ", Name=" + Name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
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
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
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
