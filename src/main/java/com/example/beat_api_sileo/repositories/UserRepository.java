package com.example.beat_api_sileo.repositories;

import com.example.beat_api_sileo.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    Optional<UserDetails> findByEmail(String email);

    Optional<User> findById(UUID id);
}
