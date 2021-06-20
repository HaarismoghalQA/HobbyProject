package com.qa.hobby.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.qa.hobby.domain.Team;

public interface TeamRepo extends JpaRepository<Team, Long>{

}
