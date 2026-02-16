package com.dhatvibs.modules.teamleads.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddExecutiveResponseDto {

    private Long executiveId;
    private String message;
}
