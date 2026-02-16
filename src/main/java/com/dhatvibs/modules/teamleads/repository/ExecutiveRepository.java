package com.dhatvibs.modules.teamleads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.dhatvibs.modules.teamleads.entity.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Long> {

    List<Executive> findByTeamLeadId(Long teamLeadId);
}
