package com.dhatvibs.modules.teamleads.dto;

import lombok.Data;

@Data
public class ExecutiveTrackingRequestDto {

    private Long executiveId;
    private Integer achieved;
    private String review;
}
