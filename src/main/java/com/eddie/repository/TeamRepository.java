package com.eddie.repository;

import com.eddie.model.Team;
import com.eddie.model.enums.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>{

    Team findOneByLeaderId(Long userId);

    List<Team> findAllByType(TeamType teamType);
}
