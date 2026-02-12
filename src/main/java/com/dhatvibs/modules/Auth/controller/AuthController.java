package com.dhatvibs.modules.Auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.dhatvibs.modules.Auth.dto.LoginRequestDto;
import com.dhatvibs.modules.Auth.dto.RegisterRequestDto;
import com.dhatvibs.modules.Auth.entity.Role;
import com.dhatvibs.modules.Auth.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService service;

    // TEAM LEAD REGISTER
    @PostMapping("/teamlead/register")
    public String registerTeamLead(@RequestBody RegisterRequestDto dto,
                                    HttpSession session) {
        return service.register(dto, Role.TEAM_LEAD, session);
    }

    // MANAGEMENT REGISTER
    @PostMapping("/management/register")
    public String registerManagement(@RequestBody RegisterRequestDto dto,
                                      HttpSession session) {
        return service.register(dto, Role.MANAGEMENT, session);
    }

    // TEAM LEAD LOGIN
    @PostMapping("/teamlead/login")
    public String loginTeamLead(@RequestBody LoginRequestDto dto,
                                 HttpSession session) {
        return service.login(dto, Role.TEAM_LEAD, session);
    }

    // MANAGEMENT LOGIN
    @PostMapping("/management/login")
    public String loginManagement(@RequestBody LoginRequestDto dto,
                                   HttpSession session) {
        return service.login(dto, Role.MANAGEMENT, session);
    }
}
