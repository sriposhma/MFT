package com.dhatvibs.modules.management.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dhatvibs.modules.management.entity.TargetAssignment;

public interface TargetAssignmentRepository
        extends JpaRepository<TargetAssignment, Long> {

    Optional<TargetAssignment> findByTeamLeadId(Long teamLeadId);
}
