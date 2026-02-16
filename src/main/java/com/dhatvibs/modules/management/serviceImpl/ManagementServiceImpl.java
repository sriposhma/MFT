package com.dhatvibs.modules.management.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import com.dhatvibs.modules.Auth.entity.AuthUser;
import com.dhatvibs.modules.Auth.entity.Role;
import com.dhatvibs.modules.Auth.repository.AuthUserRepository;
import com.dhatvibs.modules.management.dto.*;
import com.dhatvibs.modules.management.entity.TargetAssignment;
import com.dhatvibs.modules.management.repository.TargetAssignmentRepository;
import com.dhatvibs.modules.management.service.ManagementService;
import com.dhatvibs.modules.teamleads.entity.Executive;
import com.dhatvibs.modules.teamleads.entity.ExecutiveTracking;
import com.dhatvibs.modules.teamleads.repository.*;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    private TargetAssignmentRepository targetRepo;

    @Autowired
    private ExecutiveRepository executiveRepo;

    @Autowired
    private ExecutiveTrackingRepository trackingRepo;

    @Autowired
    private AuthUserRepository authRepo;

    // =============================
    // ASSIGN TARGET
    // =============================
    @Override
    public AssignTargetResponseDto assignTarget(
            AssignTargetRequestDto dto,
            HttpSession session) {

        Role role = (Role) session.getAttribute("ROLE");

        if (role != Role.MANAGEMENT) {
            throw new RuntimeException("Only Management can assign target");
        }

        TargetAssignment assignment = targetRepo
                .findByTeamLeadId(dto.getTeamLeadId())
                .orElse(TargetAssignment.builder()
                        .teamLeadId(dto.getTeamLeadId())
                        .build());

        assignment.setTotalTarget(dto.getTarget());
        targetRepo.save(assignment);

        return AssignTargetResponseDto.builder()
                .teamLeadId(dto.getTeamLeadId())
                .assignedTarget(dto.getTarget())
                .message("Target Assigned Successfully")
                .build();
    }

    // =============================
    // GET ALL TEAMLEADS
    // =============================
    @Override
    public List<TeamLeadProfileDto> getAllTeamLeadProfiles() {

        return authRepo.findAll().stream()
                .filter(user -> user.getRole() == Role.TEAM_LEAD)
                .map(user -> TeamLeadProfileDto.builder()
                        .teamLeadId(user.getId())
                        .email(user.getEmail())
                        .location(user.getLocation())
                        .build())
                .collect(Collectors.toList());
    }

    // =============================
    // WEEKLY REPORT (TEAMLEAD LEVEL)
    // =============================
    @Override
    public TeamLeadReportResponseDto getWeeklyReport(Long teamLeadId) {

        List<Executive> executives =
                executiveRepo.findByTeamLeadId(teamLeadId);

        LocalDate now = LocalDate.now();
        LocalDate start = now.with(WeekFields.ISO.getFirstDayOfWeek());
        LocalDate end = start.plusDays(6);

        int totalTarget = 0;
        int totalAchieved = 0;

        for (Executive exec : executives) {

            List<ExecutiveTracking> list =
                    trackingRepo.findByExecutiveIdAndTrackingDateBetween(
                            exec.getId(), start, end);

            totalTarget += list.stream()
                    .mapToInt(ExecutiveTracking::getTarget).sum();

            totalAchieved += list.stream()
                    .mapToInt(ExecutiveTracking::getAchieved).sum();
        }

        return TeamLeadReportResponseDto.builder()
                .totalTarget(totalTarget)
                .totalAchieved(totalAchieved)
                .totalBalance(totalTarget - totalAchieved)
                .build();
    }

    // =============================
    // MONTHLY REPORT (TEAMLEAD LEVEL)
    // =============================
    @Override
    public TeamLeadReportResponseDto getMonthlyReport(Long teamLeadId) {

        List<Executive> executives =
                executiveRepo.findByTeamLeadId(teamLeadId);

        int totalTarget = 0;
        int totalAchieved = 0;

        for (Executive exec : executives) {

            List<ExecutiveTracking> list =
                    trackingRepo.findByExecutiveId(exec.getId());

            totalTarget += list.stream()
                    .mapToInt(ExecutiveTracking::getTarget).sum();

            totalAchieved += list.stream()
                    .mapToInt(ExecutiveTracking::getAchieved).sum();
        }

        return TeamLeadReportResponseDto.builder()
                .totalTarget(totalTarget)
                .totalAchieved(totalAchieved)
                .totalBalance(totalTarget - totalAchieved)
                .build();
    }
}
