package com.dhatvibs.modules.Auth.service;

import jakarta.servlet.http.HttpSession;
import com.dhatvibs.modules.Auth.dto.LoginRequestDto;
import com.dhatvibs.modules.Auth.dto.RegisterRequestDto;
import com.dhatvibs.modules.Auth.entity.Role;

public interface AuthService {

    String register(RegisterRequestDto dto, Role role, HttpSession session);

    String login(LoginRequestDto dto, Role role, HttpSession session);
}
