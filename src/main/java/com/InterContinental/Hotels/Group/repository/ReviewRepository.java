package com.InterContinental.Hotels.Group.repository;

import com.InterContinental.Hotels.Group.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}