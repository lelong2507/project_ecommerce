// package com.example.ProjectBE.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.ProjectBE.repository.UserRepository;
// import com.example.ProjectBE.entities.User;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     UserRepository userRepository;

//     @Override
//     @Transactional
//     public UserDetails loadUserByUsername(String email)
//             throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(email).orElse(null);

//         if (user == null) {
//             throw new UsernameNotFoundException("User not found with email : " + email);
//         }
//         return UserPrincipal.create(user);
//     }

//     @Transactional
//     public UserDetails loadUserBusById(Integer id) {
//         User user = userRepository.getReferenceById(id);
//         return UserPrincipal.create(user);
//     }

//     @Transactional
//     public UserDetails loadUserById(Integer id) {
//         User user = userRepository.getReferenceById(id);
//         return UserPrincipal.create(user);
//     }

// }