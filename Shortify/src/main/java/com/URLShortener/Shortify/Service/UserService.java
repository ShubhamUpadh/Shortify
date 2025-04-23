package com.URLShortener.Shortify.Service;

import com.URLShortener.Shortify.Model.UserModel;
import com.URLShortener.Shortify.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public ResponseEntity<String> createNewUser(UserModel userModel){
        //check if the user exists already
        if (userRepository.existsByUsername(userModel.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Please choose a different username");
        }
        userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

}
