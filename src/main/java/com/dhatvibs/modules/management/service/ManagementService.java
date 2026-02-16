package com.dhatvibs.modules.management.service;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import com.dhatvibs.modules.management.dto.*;

public interface ManagementService {

    AssignTargetResponseDto assignTarget(
            AssignTargetRequestDto dto,
            HttpSession session);

    List<TeamLeadProfileDto> getAllTeamLeadProfiles();

    TeamLeadReportResponseDto getWeeklyReport(Long teamLeadId);

    TeamLeadReportResponseDto getMonthlyReport(Long teamLeadId);
}
