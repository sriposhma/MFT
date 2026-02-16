package com.dhatvibs.modules.teamleads.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecutiveTrackingResponseDto {

    private Integer target;
    private Integer achieved;
    private Integer balance;
    private String message;
}
