package com.InterContinental.Hotels.Group.repository;

import com.InterContinental.Hotels.Group.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}