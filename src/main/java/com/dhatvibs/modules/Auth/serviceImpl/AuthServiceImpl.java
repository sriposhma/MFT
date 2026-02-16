package com.dhatvibs.modules.Auth.serviceImpl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import com.dhatvibs.modules.Auth.dto.LoginRequestDto;
import com.dhatvibs.modules.Auth.dto.ManagementRegisterRequestDto;
import com.dhatvibs.modules.Auth.dto.TeamLeadRegisterRequestDto;
import com.dhatvibs.modules.Auth.entity.AuthUser;
import com.dhatvibs.modules.Auth.entity.Role;
import com.dhatvibs.modules.Auth.repository.AuthUserRepository;
import com.dhatvibs.modules.Auth.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserRepository repository;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@dhatvibs\\.com$";

	/*
	 * private static final String PASSWORD_REGEX =
	 * "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{6,}$";
	 */
    
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";
    
    //@Autowired
    //private TeamLeadRepository teamLeadRepository;


    
    
	
    
    @Override
    public String registerTeamLead(TeamLeadRegisterRequestDto dto, HttpSession session) {

        validateCommon(dto.getEmail(), dto.getPassword(), dto.getConfirmPassword());

        if (dto.getLocation() == null || dto.getLocation().isBlank()) {
            throw new RuntimeException("Location is required for Team Lead");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User already registered");
        }

        AuthUser user = AuthUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.TEAM_LEAD)
                .location(dto.getLocation())
                .build();

        repository.save(user);
        
       // AuthUser savedUser = repository.save(user);

		/*
		 * // 🔥 CREATE TEAMLEAD ENTRY TeamLead teamLead = TeamLead.builder()
		 * .name(savedUser.getEmail()) // or separate name field if available
		 * .assignedTarget(0) // default target .authUser(savedUser) .build();
		 * 
		 * teamLeadRepository.save(teamLead);
		 */

        session.setAttribute("USER_EMAIL", user.getEmail());
        session.setAttribute("ROLE", user.getRole());
        session.setAttribute("USER_ID", user.getId());

       // return "Team Lead Registration successful";
        return "TeamLead Registration successful. SessionId: " + session.getId();

    }
    	
    
    @Override
    public String registerManagement(ManagementRegisterRequestDto dto, HttpSession session) {

        validateCommon(dto.getEmail(), dto.getPassword(), dto.getConfirmPassword());

        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User already registered");
        }

        AuthUser user = AuthUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.MANAGEMENT)
                .location(null)   // No location
                .build();

        repository.save(user);
        
        session.setAttribute("USER_ID", user.getId());
        session.setAttribute("USER_EMAIL", user.getEmail());
        session.setAttribute("ROLE", user.getRole());

       // return "Management Registration successful";
        return "Management Registration successful. SessionId: " + session.getId();

    }


    
    
    @Override
    public String login(LoginRequestDto dto, Role role, HttpSession session) {

        AuthUser user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.getRole().equals(role)) {
            throw new RuntimeException("Role mismatch");
        }

        // Create HTTP Session
        session.setAttribute("USER_ID", user.getId());
        session.setAttribute("USER_EMAIL", user.getEmail());
        session.setAttribute("ROLE", user.getRole());

      //  return "Login successful";
        
        return "Login successful. SessionId: " + session.getId();

    }  
    
    
    private void validateCommon(String email, String password, String confirmPassword) {

        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new RuntimeException("Email must be @dhatvibs.com only");
        }

        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            throw new RuntimeException(
                    "Password must contain minimum 8 characters with uppercase, lowercase, number & special character");
        }
    }

} 
