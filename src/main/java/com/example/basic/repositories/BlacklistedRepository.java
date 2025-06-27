package com.example.basic.repositories;

import com.example.basic.entities.Blacklisted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistedRepository extends JpaRepository<Blacklisted, Long> {
    boolean existsByToken(String token);
}
