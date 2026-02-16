package com.dhatvibs.modules.teamleads.service;

import jakarta.servlet.http.HttpSession;
import com.dhatvibs.modules.teamleads.dto.*;

public interface TeamLeadService {

    AddExecutiveResponseDto addExecutive(AddExecutiveRequestDto dto,
                                         HttpSession session);

    ExecutiveTrackingResponseDto trackExecutive(
            ExecutiveTrackingRequestDto dto,
            HttpSession session);

    WeeklyMonthlyResponseDto getWeeklyData(Long executiveId);

    WeeklyMonthlyResponseDto getMonthlyData(Long executiveId);
}
