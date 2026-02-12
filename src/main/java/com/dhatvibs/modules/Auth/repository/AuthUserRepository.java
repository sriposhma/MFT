package com.dhatvibs.modules.Auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dhatvibs.modules.Auth.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
