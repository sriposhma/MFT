package com.dhatvibs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/*
 * @Configuration public class SecurityConfig {
 * 
 * 
 * 
 * 
 * 
 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
 * Exception {
 * 
 * http .cors(cors -> {}) // ENABLE CORS .csrf(csrf -> csrf.disable())
 * .authorizeHttpRequests(auth -> auth .requestMatchers( "/swagger-ui.html",
 * "/swagger-ui/**", "/v3/api-docs/**", "/api/**" ).permitAll()
 * .anyRequest().authenticated() ) .formLogin(form -> form.disable());
 * 
 * return http.build(); }
 * 
 * 
 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
 * Exception {
 * 
 * http .cors(cors -> {}) .csrf(csrf -> csrf.disable())
 * .sessionManagement(session -> session.sessionCreationPolicy(
 * org.springframework.security.config.http.SessionCreationPolicy.ALWAYS ) )
 * .authorizeHttpRequests(auth -> auth .requestMatchers( "/swagger-ui.html",
 * "/swagger-ui/**", "/v3/api-docs/**", "/api/teamlead/register",
 * "/api/teamlead/login", "/api/management/register", "/api/management/login"
 * ).permitAll() .anyRequest().authenticated() // 🔥 Protect all other APIs )
 * .formLogin(form -> form.disable());
 * 
 * return http.build(); }
 * 
 * }
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())   // 🔥 VERY IMPORTANT
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/api/teamlead/register",
                            "/api/teamlead/login",
                            "/api/management/register",
                            "/api/management/login",
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    ).permitAll()
                    .anyRequest().permitAll()   // 🔥 allow all for now
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }
}
