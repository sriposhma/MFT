package com.dhatvibs.modules.teamleads.dto;

import lombok.Data;

@Data
public class AddExecutiveRequestDto {

    private String name;
    private String phone;
    private String employmentType; // FT / PT
}
