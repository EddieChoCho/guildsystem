package com.eddie.repository;

import com.eddie.model.ExperimentalTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperimentalTeamRepository extends JpaRepository<ExperimentalTeam, Long> {

    List<ExperimentalTeam> findAllByAdvisorId(Long advisorId);
}
