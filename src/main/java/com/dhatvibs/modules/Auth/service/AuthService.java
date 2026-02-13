package com.dhatvibs.modules.Auth.service;

import jakarta.servlet.http.HttpSession;
import com.dhatvibs.modules.Auth.dto.LoginRequestDto;
import com.dhatvibs.modules.Auth.dto.ManagementRegisterRequestDto;
import com.dhatvibs.modules.Auth.dto.RegisterRequestDto;
import com.dhatvibs.modules.Auth.dto.TeamLeadRegisterRequestDto;
import com.dhatvibs.modules.Auth.entity.Role;

/*
 * public interface AuthService {
 * 
 * String register(RegisterRequestDto dto, Role role, HttpSession session);
 * 
 * String login(LoginRequestDto dto, Role role, HttpSession session); }
 */ 

public interface AuthService {

    String registerTeamLead(TeamLeadRegisterRequestDto dto, HttpSession session);

    String registerManagement(ManagementRegisterRequestDto dto, HttpSession session);

    String login(LoginRequestDto dto, Role role, HttpSession session);
}

