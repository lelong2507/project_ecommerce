package com.example.ProjectBE.controller;

import com.example.ProjectBE.dto.request.UserDTO.UserCreationRequest;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-user")
    ResponseEntity<?> createUser(@RequestBody UserCreationRequest req) {
        System.out.println(req.toString());

        try {
            User user = new User();
            user.setFirstName(req.getFirstName());
            user.setUserName(req.getUserName());
            user.setPassWord(req.getPassWord());
            user.setRole(req.getRole());
            userService.registerUser(req);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
