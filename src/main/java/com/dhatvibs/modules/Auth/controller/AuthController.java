package com.dhatvibs.modules.Auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.dhatvibs.modules.Auth.dto.LoginRequestDto;
import com.dhatvibs.modules.Auth.dto.ManagementRegisterRequestDto;
import com.dhatvibs.modules.Auth.dto.TeamLeadRegisterRequestDto;
import com.dhatvibs.modules.Auth.entity.Role;
import com.dhatvibs.modules.Auth.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService service;

	
    
 // TEAM LEAD REGISTER
    @PostMapping("/teamlead/register")
    public String registerTeamLead(@RequestBody TeamLeadRegisterRequestDto dto,
                                    HttpSession session) {
        return service.registerTeamLead(dto, session);
    }

    // MANAGEMENT REGISTER
    @PostMapping("/management/register")
    public String registerManagement(@RequestBody ManagementRegisterRequestDto dto,
                                      HttpSession session) {
        return service.registerManagement(dto, session);
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
