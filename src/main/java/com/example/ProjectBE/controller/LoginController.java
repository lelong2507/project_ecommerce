package com.example.ProjectBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.dto.request.UserDTO.UserCreationRequest;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.payload.LoginRequest;
import com.example.ProjectBE.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/handle-login")
    ResponseEntity<?> loginMethod(@RequestBody LoginRequest loginRequest, HttpSession session) {
        System.out.println(loginRequest.toString());
        try {
            User user = userService.login(loginRequest.getUserName(), loginRequest.getPassWord());
            if (user != null) {
                session.setAttribute("userId", user.getIdUser());
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("role", user.getRole());

                UserCreationRequest userDTO = new UserCreationRequest(user.getFirstName(), user.getUserName(),
                        user.getPassWord(), user.getRole());
                System.out.println(userDTO.toString());
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email / password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Error");
        }
    }

    @PostMapping("/session-info")
    public ResponseEntity<?> getSessionInfo(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            String userName = (String) session.getAttribute("userName");
            return new ResponseEntity<>("Logged in user: " + userName, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }

}
