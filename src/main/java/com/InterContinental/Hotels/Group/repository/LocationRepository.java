package com.InterContinental.Hotels.Group.repository;

import com.InterContinental.Hotels.Group.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}