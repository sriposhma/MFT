package com.dhatvibs.modules.teamleads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import com.dhatvibs.modules.teamleads.entity.ExecutiveTracking;

public interface ExecutiveTrackingRepository
        extends JpaRepository<ExecutiveTracking, Long> {

    List<ExecutiveTracking> findByExecutiveIdAndTrackingDateBetween(
            Long executiveId,
            LocalDate start,
            LocalDate end
    );

    List<ExecutiveTracking> findByExecutiveId(Long executiveId);
}
