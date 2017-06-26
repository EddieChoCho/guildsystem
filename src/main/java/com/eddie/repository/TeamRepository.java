package com.eddie.repository;

import com.eddie.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TeamRepository extends AbstractTeamRepository, JpaRepository<Team, Long>{
}
