package com.example.ProjectBE.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.payload.request.LoginRequest;
import com.example.ProjectBE.payload.response.LoginResponse;
import com.example.ProjectBE.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginMethod(@RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userService.loginMethod(loginRequest.getUserName(), loginRequest.getPassWord());
        System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("role", user.getRole());
            session.setMaxInactiveInterval(600);
            return ResponseEntity.ok(new LoginResponse("Logged Successfully", user));
        } else {
            return ResponseEntity.status(401).body("Invalid userName or passWord");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

}
