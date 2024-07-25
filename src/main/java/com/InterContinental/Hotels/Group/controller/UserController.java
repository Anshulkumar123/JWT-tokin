package com.InterContinental.Hotels.Group.controller;

import com.InterContinental.Hotels.Group.dto.LoginDto;
import com.InterContinental.Hotels.Group.dto.PropertyUserDto;
import com.InterContinental.Hotels.Group.dto.TokenResponse;
import com.InterContinental.Hotels.Group.entity.PropertyUser;
import com.InterContinental.Hotels.Group.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto propertyUserDto){
        PropertyUser propertyUser = userService.addUser(propertyUserDto);
        if (propertyUser != null){
            return new ResponseEntity<>("Registration is successful", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if (token != null){
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("in valid credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public PropertyUser getCurrentUserProfile(@AuthenticationPrincipal PropertyUser user){
        return user;
    }
}
