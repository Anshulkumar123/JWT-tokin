package com.InterContinental.Hotels.Group.repository;

import com.InterContinental.Hotels.Group.entity.Property;
import com.InterContinental.Hotels.Group.entity.PropertyUser;
import com.InterContinental.Hotels.Group.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
//    @Query("select r from Review r where r.property=:propertyId and r.propertyUser=:userId")
//    Review findReviewByUserIdAndPropertyId(@Param("propertyId") long userId, @Param("userId") long propertyId);

    @Query("select r from Review r where r.property=:property and r.propertyUser=:user")
    Review findReviewByUser(@Param("property") Property property, @Param("user") PropertyUser user);

    List<Review> findByPropertyUser(PropertyUser user);

}