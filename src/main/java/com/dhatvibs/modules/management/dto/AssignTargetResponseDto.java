package com.dhatvibs.modules.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignTargetResponseDto {

    private Long teamLeadId;
    private Integer assignedTarget;
    private String message;
}
