package com.qa.hobby.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobby.domain.Player;


@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
	

	
}
