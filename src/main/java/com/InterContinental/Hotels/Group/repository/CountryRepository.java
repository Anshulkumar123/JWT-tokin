package com.InterContinental.Hotels.Group.repository;

import com.InterContinental.Hotels.Group.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}