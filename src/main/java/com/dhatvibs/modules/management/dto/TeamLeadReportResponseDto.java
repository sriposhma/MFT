package com.dhatvibs.modules.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamLeadReportResponseDto {

    private Integer totalTarget;
    private Integer totalAchieved;
    private Integer totalBalance;
}
