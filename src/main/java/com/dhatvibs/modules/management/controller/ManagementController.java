package com.dhatvibs.modules.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import com.dhatvibs.modules.management.dto.*;
import com.dhatvibs.modules.management.service.ManagementService;

@RestController
@RequestMapping("/api")
public class ManagementController {

    @Autowired
    private ManagementService service;

    @PostMapping("/management/assignTarget")
    public AssignTargetResponseDto assignTarget(
            @RequestBody AssignTargetRequestDto dto,
            HttpSession session) {

        return service.assignTarget(dto, session);
    }

    @GetMapping("/teamleads/profiles")
    public List<TeamLeadProfileDto> getProfiles() {
        return service.getAllTeamLeadProfiles();
    }

    @GetMapping("/teamleads/weeklyreport")
    public TeamLeadReportResponseDto weeklyReport(
            @RequestParam Long teamLeadId) {

        return service.getWeeklyReport(teamLeadId);
    }

    @GetMapping("/teamleads/monthlyreport")
    public TeamLeadReportResponseDto monthlyReport(
            @RequestParam Long teamLeadId) {

        return service.getMonthlyReport(teamLeadId);
    }
}
