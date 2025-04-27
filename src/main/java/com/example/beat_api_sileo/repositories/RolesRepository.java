package com.example.beat_api_sileo.repositories;

import com.example.beat_api_sileo.domain.Roles.RoleEnum;
import com.example.beat_api_sileo.domain.Roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RolesRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findByName(RoleEnum name);
}
