package com.example.ProjectBE.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(nullable = false, name = "pass_word")
    String passWord;
    @Column(nullable = false, name = "first_name")
    String firstName;
    @Column(nullable = false, name = "last_name")
    String lastName;
    @Column(nullable = false, name = "address")
    String address;
    @Column(nullable = false, name = "phone_number")
    String phoneNumber;
    @Column(nullable = false, name = "user_dob")
    @DateTimeFormat(pattern = ("yyyy-MM-dd"))
    Date userDob;
    @Column(nullable = false, name = "avatar")
    String avatar;
    @Column(nullable = false, name = "status")
    boolean status;
    @Column(nullable = false, name = "role")
    String role;
}
