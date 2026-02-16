package com.dhatvibs.modules.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamLeadProfileDto {

    private Long teamLeadId;
    private String email;
    private String location;
}
