package com.dhatvibs.modules.teamleads.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;

import com.dhatvibs.modules.teamleads.dto.*;
import com.dhatvibs.modules.teamleads.entity.*;
import com.dhatvibs.modules.teamleads.repository.*;
import com.dhatvibs.modules.teamleads.service.TeamLeadService;
import com.dhatvibs.modules.management.repository.TargetAssignmentRepository;

@Service
public class TeamLeadServiceImpl implements TeamLeadService {

    @Autowired
    private ExecutiveRepository executiveRepo;

    @Autowired
    private ExecutiveTrackingRepository trackingRepo;

    @Autowired
    private TargetAssignmentRepository targetRepo;

    // ADD EXECUTIVE
    @Override
    public AddExecutiveResponseDto addExecutive(
            AddExecutiveRequestDto dto,
            HttpSession session) {

        Long teamLeadId = (Long) session.getAttribute("USER_ID");

        Executive executive = Executive.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .employmentType(dto.getEmploymentType())
                .teamLeadId(teamLeadId)
                .build();

        executiveRepo.save(executive);

        return AddExecutiveResponseDto.builder()
                .executiveId(executive.getId())
                .message("Executive Added Successfully")
                .build();
    }

    // DAILY TRACKING
    @Override
    public ExecutiveTrackingResponseDto trackExecutive(
            ExecutiveTrackingRequestDto dto,
            HttpSession session) {

        Executive executive = executiveRepo.findById(dto.getExecutiveId())
                .orElseThrow(() -> new RuntimeException("Executive not found"));

        int totalTarget = targetRepo
                .findByTeamLeadId(executive.getTeamLeadId())
                .orElseThrow(() -> new RuntimeException("Target not assigned"))
                .getTotalTarget();

        int totalExecutives =
                executiveRepo.findByTeamLeadId(executive.getTeamLeadId()).size();

        int splitTarget = totalTarget / totalExecutives;
        int balance = splitTarget - dto.getAchieved();

        ExecutiveTracking tracking = ExecutiveTracking.builder()
                .executiveId(dto.getExecutiveId())
                .target(splitTarget)
                .achieved(dto.getAchieved())
                .balance(balance)
                .review(dto.getReview())
                .trackingDate(LocalDate.now())
                .build();

        trackingRepo.save(tracking);

        return ExecutiveTrackingResponseDto.builder()
                .target(splitTarget)
                .achieved(dto.getAchieved())
                .balance(balance)
                .message("Tracking Saved Successfully")
                .build();
    }

    // WEEKLY
    @Override
    public WeeklyMonthlyResponseDto getWeeklyData(Long executiveId) {

        LocalDate now = LocalDate.now();
        LocalDate start = now.with(WeekFields.ISO.getFirstDayOfWeek());
        LocalDate end = start.plusDays(6);

        List<ExecutiveTracking> list =
                trackingRepo.findByExecutiveIdAndTrackingDateBetween(
                        executiveId, start, end);

        int totalTarget = list.stream()
                .mapToInt(ExecutiveTracking::getTarget).sum();

        int totalAchieved = list.stream()
                .mapToInt(ExecutiveTracking::getAchieved).sum();

        return WeeklyMonthlyResponseDto.builder()
                .totalTarget(totalTarget)
                .totalAchieved(totalAchieved)
                .totalBalance(totalTarget - totalAchieved)
                .build();
    }

    // MONTHLY
    @Override
    public WeeklyMonthlyResponseDto getMonthlyData(Long executiveId) {

        List<ExecutiveTracking> list =
                trackingRepo.findByExecutiveId(executiveId);

        int totalTarget = list.stream()
                .mapToInt(ExecutiveTracking::getTarget).sum();

        int totalAchieved = list.stream()
                .mapToInt(ExecutiveTracking::getAchieved).sum();

        return WeeklyMonthlyResponseDto.builder()
                .totalTarget(totalTarget)
                .totalAchieved(totalAchieved)
                .totalBalance(totalTarget - totalAchieved)
                .build();
    }
}
