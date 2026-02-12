package com.dhatvibs.modules.Auth.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String email;
    private String password;
    private String confirmPassword;
}
