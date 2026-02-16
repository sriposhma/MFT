package com.dhatvibs.modules.teamleads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.dhatvibs.modules.teamleads.dto.*;
import com.dhatvibs.modules.teamleads.service.TeamLeadService;

@RestController
@RequestMapping("/api")
public class TeamLeadController {

    @Autowired
    private TeamLeadService service;

    @PostMapping("/teamlead/addExecutives")
    public AddExecutiveResponseDto addExecutive(
            @RequestBody AddExecutiveRequestDto dto,
            HttpSession session) {

        return service.addExecutive(dto, session);
    }

    @PostMapping("/executive/track")
    public ExecutiveTrackingResponseDto track(
            @RequestBody ExecutiveTrackingRequestDto dto,
            HttpSession session) {

        return service.trackExecutive(dto, session);
    }

    @GetMapping("/executive/weeklyData")
    public WeeklyMonthlyResponseDto weekly(
            @RequestParam Long executiveId) {

        return service.getWeeklyData(executiveId);
    }

    @GetMapping("/executive/monthlyData")
    public WeeklyMonthlyResponseDto monthly(
            @RequestParam Long executiveId) {

        return service.getMonthlyData(executiveId);
    }
}
