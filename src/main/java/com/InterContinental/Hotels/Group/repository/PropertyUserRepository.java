package com.InterContinental.Hotels.Group.repository;

import com.InterContinental.Hotels.Group.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {

    Optional<PropertyUser> findByUsername(String username);
}