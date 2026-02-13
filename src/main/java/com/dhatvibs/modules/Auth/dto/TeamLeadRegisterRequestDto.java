package com.dhatvibs.modules.Auth.dto;

import lombok.Data;

@Data
public class TeamLeadRegisterRequestDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String location;  // ONLY for TeamLead
}
