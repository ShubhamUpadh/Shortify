package com.URLShortener.Shortify.Controller;

import com.URLShortener.Shortify.Model.UserModel;
import com.URLShortener.Shortify.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createNewUser(@RequestBody UserModel userModel){
        if (userModel.getUsername() == null || userModel.getPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and/or Password can't be null");
        }
        return userService.createNewUser(userModel);
    }
}
