package com.InterContinental.Hotels.Group.controller;

import com.InterContinental.Hotels.Group.entity.Property;
import com.InterContinental.Hotels.Group.entity.PropertyUser;
import com.InterContinental.Hotels.Group.entity.Review;
import com.InterContinental.Hotels.Group.repository.PropertyRepository;
import com.InterContinental.Hotels.Group.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<String> addReview(
            @PathVariable long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser user){

        Review r = reviewRepository.findReviewByUserIdAndPropertyId(propertyId, user.getId());
        if (r!=null){
            return new ResponseEntity<>("You have already added a review for this property", HttpStatus.BAD_REQUEST);
        }
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();
        review.setProperty(property);
        review.setPropertyUser(user);

        reviewRepository.save(review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
    }
}
