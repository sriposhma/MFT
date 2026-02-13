package com.dhatvibs.modules.Auth.serviceImpl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import com.dhatvibs.modules.Auth.dto.LoginRequestDto;
import com.dhatvibs.modules.Auth.dto.ManagementRegisterRequestDto;
import com.dhatvibs.modules.Auth.dto.RegisterRequestDto;
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

    
    
	/*
	 * @Override public String register(RegisterRequestDto dto, Role role,
	 * HttpSession session) {
	 * 
	 * // Email validation if (!Pattern.matches(EMAIL_REGEX, dto.getEmail())) {
	 * throw new RuntimeException("Email must be @dhatvibs.com only"); }
	 * 
	 * // Password match check if
	 * (!dto.getPassword().equals(dto.getConfirmPassword())) { throw new
	 * RuntimeException("Passwords do not match"); }
	 * 
	 * // Password validation if (!Pattern.matches(PASSWORD_REGEX,
	 * dto.getPassword())) { throw new RuntimeException(
	 * "Password must contain 6 characters with uppercase, lowercase, number & special character"
	 * ); }
	 * 
	 * // Check existing user if (repository.existsByEmail(dto.getEmail())) { throw
	 * new RuntimeException("User already registered"); }
	 * 
	 * AuthUser user = AuthUser.builder() .email(dto.getEmail())
	 * .password(dto.getPassword()) // later you can encode .role(role) .build();
	 * 
	 * repository.save(user);
	 * 
	 * // Create HTTP Session session.setAttribute("USER_EMAIL", user.getEmail());
	 * session.setAttribute("ROLE", user.getRole());
	 * 
	 * return "Registration successful"; }
	 */  
    
	/*
	 * @Override public String register(RegisterRequestDto dto, Role role,
	 * HttpSession session) {
	 * 
	 * if (!Pattern.matches(EMAIL_REGEX, dto.getEmail())) { throw new
	 * RuntimeException("Email must be @dhatvibs.com only"); }
	 * 
	 * if (!dto.getPassword().equals(dto.getConfirmPassword())) { throw new
	 * RuntimeException("Passwords do not match"); }
	 * 
	 * if (!Pattern.matches(PASSWORD_REGEX, dto.getPassword())) { throw new
	 * RuntimeException(
	 * "Password must contain minimum 8 characters with uppercase, lowercase, number & special character"
	 * ); }
	 * 
	 * if (repository.existsByEmail(dto.getEmail())) { throw new
	 * RuntimeException("User already registered"); }
	 * 
	 * // 🔥 Location required only for TEAM_LEAD if (role == Role.TEAM_LEAD) { if
	 * (dto.getLocation() == null || dto.getLocation().isBlank()) { throw new
	 * RuntimeException("Location is required for Team Lead"); } }
	 * 
	 * AuthUser user = AuthUser.builder() .email(dto.getEmail())
	 * .password(dto.getPassword()) .role(role) .location(role == Role.TEAM_LEAD ?
	 * dto.getLocation() : null) .build();
	 * 
	 * repository.save(user);
	 * 
	 * session.setAttribute("USER_EMAIL", user.getEmail());
	 * session.setAttribute("ROLE", user.getRole());
	 * 
	 * return "Registration successful"; }
	 */ 
    
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

        session.setAttribute("USER_EMAIL", user.getEmail());
        session.setAttribute("ROLE", user.getRole());

        return "Team Lead Registration successful";
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

        session.setAttribute("USER_EMAIL", user.getEmail());
        session.setAttribute("ROLE", user.getRole());

        return "Management Registration successful";
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
        session.setAttribute("USER_EMAIL", user.getEmail());
        session.setAttribute("ROLE", user.getRole());

        return "Login successful";
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
