package com.InterContinental.Hotels.Group.service;

import com.InterContinental.Hotels.Group.dto.LoginDto;
import com.InterContinental.Hotels.Group.dto.PropertyUserDto;
import com.InterContinental.Hotels.Group.entity.PropertyUser;
import com.InterContinental.Hotels.Group.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {
    private PropertyUserRepository userRepository;

    private JWtService jWtService;

    public UserService(PropertyUserRepository userRepository, JWtService jWtService) {
        this.userRepository = userRepository;
        this.jWtService = jWtService;
    }

    public PropertyUser addUser(PropertyUserDto propertyUserDto){
        PropertyUser user = new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setUsername(propertyUserDto.getUsername());
        user.setEmail(propertyUserDto.getEmail());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(), BCrypt.gensalt(10)));
        user.setUserRole(propertyUserDto.getUserRole());
        PropertyUser savedUser = userRepository.save(user);
        return savedUser;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<PropertyUser> opUser = userRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            PropertyUser propertyUser = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword())){
                return jWtService.generateToken(propertyUser);
            }
        }
        return null;
    }
}
