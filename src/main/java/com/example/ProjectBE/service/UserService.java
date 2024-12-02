package com.example.ProjectBE.service;

import com.example.ProjectBE.dto.request.EmailRequest;
import com.example.ProjectBE.dto.request.UserDTO.UserCreationRequest;
import com.example.ProjectBE.dto.request.UserDTO.UserDetailRequest;
import com.example.ProjectBE.dto.request.UserDTO.UserUpdateRequest;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.repository.UserRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Show list
    public List<User> showAllUser() {
        return userRepository.findAll();
    }

    // Login Method
    public User loginMethod(String userName, String passWord) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(passWord, user.getPassWord())) {
                return user;
            }
        }

        return null;
    }

    // Register Method
    public User registerUser(UserCreationRequest req) {

        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setUserName(req.getUserName());
        user.setEmail("Edit email@example.com");
        String hashedPassword = BCrypt.hashpw(req.getPassWord(), BCrypt.gensalt());
        user.setPassWord(hashedPassword);
        user.setRole(req.getRole());
        user.setLastName("Edit yourLastName");
        user.setAddress("Edit yourAddress");
        user.setPhoneNumber("Edit yourPhoneNumber");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dobDate = simpleDateFormat.parse("2003-01-01");
            user.setUserDob(dobDate);
        } catch (ParseException e) {
            System.out.println(e);
        }

        user.setAvatar("Edit your avatar");
        user.setStatus(true);
        return userRepository.save(user);
    }

    // Login GG method
    public User findOrCreateUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        User user = new User();
        user.setEmail(email);
        user.setUserName(email);
        user.setRole("user");
        user.setStatus(true);
        return userRepository.save(user);
    }

    public void sendEmail(EmailRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("chumlu2102@gmail.com");
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody(), true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        } 
    }

    public UserDetailRequest getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setUserName(user.getUserName());
        userDetailRequest.setFirstName(user.getFirstName());
        userDetailRequest.setLastName(user.getLastName());
        userDetailRequest.setAddress(user.getAddress());
        userDetailRequest.setPhoneNumber(user.getPhoneNumber());
        userDetailRequest.setUserDob(user.getUserDob());
        userDetailRequest.setAvatar(user.getAvatar());

        return userDetailRequest;
    }

    public void deleteUser(int idUser) {
        userRepository.deleteById(idUser);
    }

    @Transactional
    public void blockUser(int idUser) {
        User user = userRepository.getReferenceById(idUser);
        user.setStatus(false);
    }

    @Transactional
    public void unBan(int idUser) {
        User user = userRepository.getReferenceById(idUser);
        user.setStatus(true);
    }

    @Transactional
    public User updateUser(int id, UserUpdateRequest req) {
        User user = userRepository.getReferenceById(id);
        if (user != null) {
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setAddress(req.getAddress());
            user.setPhoneNumber(req.getPhoneNumber());
            user.setUserDob(req.getUserDob());
            user.setAvatar(req.getAvatar());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User with id " + id + " not found.");
        }
    }

    public UserDetailRequest getUserByUserName(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setUserName(user.getUserName());
        userDetailRequest.setFirstName(user.getFirstName());
        userDetailRequest.setLastName(user.getLastName());
        userDetailRequest.setEmail(user.getEmail());
        userDetailRequest.setAddress(user.getAddress());
        userDetailRequest.setPhoneNumber(user.getPhoneNumber());
        userDetailRequest.setUserDob(user.getUserDob());
        userDetailRequest.setAvatar(user.getAvatar());

        return userDetailRequest;
    }

    public UserDetailRequest getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setUserName(user.getUserName());
        userDetailRequest.setFirstName(user.getFirstName());
        userDetailRequest.setLastName(user.getLastName());
        userDetailRequest.setEmail(user.getEmail());
        userDetailRequest.setAddress(user.getAddress());
        userDetailRequest.setPhoneNumber(user.getPhoneNumber());
        userDetailRequest.setUserDob(user.getUserDob());
        userDetailRequest.setAvatar(user.getAvatar());

        return userDetailRequest;
    }

}
