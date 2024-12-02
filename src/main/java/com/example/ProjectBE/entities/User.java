package com.example.ProjectBE.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_user")
    int idUser;
    @Column(nullable = false, name = "user_name")
    String userName;
    @Column(name = "email")
    String email;
    @Column(nullable = false, name = "pass_word")
    String passWord;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "address")
    String address;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "user_dob")
    @DateTimeFormat(pattern = ("yyyy-MM-dd"))
    Date userDob;
    @Column(name = "avatar")
    String avatar;
    @Column(name = "status")
    boolean status;
    @Column(nullable = false, name = "role")
    String role;
}
