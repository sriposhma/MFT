package com.dhatvibs.modules.Auth.dto;

import lombok.Data;

@Data
public class ManagementRegisterRequestDto {

    private String email;
    private String password;
    private String confirmPassword;
}
