package com.dhatvibs.modules.teamleads.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeeklyMonthlyResponseDto {

    private Integer totalTarget;
    private Integer totalAchieved;
    private Integer totalBalance;
}
