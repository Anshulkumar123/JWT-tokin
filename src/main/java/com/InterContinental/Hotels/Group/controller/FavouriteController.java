package com.InterContinental.Hotels.Group.controller;

import com.InterContinental.Hotels.Group.entity.Favourite;
import com.InterContinental.Hotels.Group.entity.PropertyUser;
import com.InterContinental.Hotels.Group.repository.FavouriteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    private FavouriteRepository favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @PostMapping
    public ResponseEntity<Favourite> addFavourite(
            @RequestBody Favourite favourite,
            @AuthenticationPrincipal PropertyUser user
    ){
        favourite.setPropertyUser(user);
        Favourite savedFavourite = favouriteRepository.save(favourite);
        return new ResponseEntity<>(savedFavourite, HttpStatus.CREATED);
    }

}
