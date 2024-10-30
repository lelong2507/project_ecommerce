// package com.example.ProjectBE.utils;

// import java.util.Base64;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class EncodingUtils {

//     private final BCryptPasswordEncoder passwordEncoder;

//     public EncodingUtils() {
//         this.passwordEncoder = new BCryptPasswordEncoder();
//     }

//     // ID Encoding
//     public String encode(Long id) {
//         return Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());
//     }

//     public Long decode(String encodedID) {
//         return Long.valueOf(new String(Base64.getDecoder().decode(encodedID)));
//     }

//     // PassWord Encoding
//     public String encodePassWord(String passWord) {
//         return passwordEncoder.encode(passWord);
//     }

//     public boolean matches(String rawPassword, String encodedPassWord) {
//         return passwordEncoder.matches(rawPassword, encodedPassWord);
//     }
// }
