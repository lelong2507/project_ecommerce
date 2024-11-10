package com.example.ProjectBE.controller;

import com.example.ProjectBE.dto.request.UserDTO.UserCreationRequest;
import com.example.ProjectBE.dto.request.UserDTO.UserDetailRequest;
import com.example.ProjectBE.dto.request.UserDTO.UserUpdateRequest;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> showListUser() {
        System.out.println("List User Method is running");
        return userService.showAllUser();
    }

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

    @GetMapping("/user-detail/{id}")
    ResponseEntity<?> getUserDetail(@PathVariable(name = "id") String id) {
        try {
            int userId = Integer.parseInt(id);
            UserDetailRequest request = userService.getUserById(userId);
            System.out.println(request);
            if (request == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid user ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteUser(@PathVariable(name = "id") String id) {
        System.out.println("Delete method is running");
        try {
            int userId = Integer.parseInt(id);
            userService.deleteUser(userId);
            System.out.println(userId + " has been deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid user ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-infor/{id}")
    ResponseEntity<?> updateUser(@PathVariable(name = "id") String id, @RequestBody UserUpdateRequest request) {
        try {
            int userId = Integer.parseInt(id);
            User updatedUser = userService.updateUser(userId, request);
            System.out.println("User id:" + id + "has been updated " + request.toString());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid user ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/block-user/{id}")
    ResponseEntity<?> blockUser(@PathVariable(name = "id") String id) {
        try {
            int idUser = Integer.parseInt(id);
            userService.blockUser(idUser);
            System.out.println("User id:" + idUser + " has been blocked");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid user ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unban-user/{id}")
    ResponseEntity<?> unbanUser(@PathVariable(name = "id") String id) {
        try {
            int idUser = Integer.parseInt(id);
            userService.unBan(idUser);
            System.out.println("User id:" + idUser + " has been unBlocked");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid user ID format", HttpStatus.BAD_REQUEST);
        }
    }
}