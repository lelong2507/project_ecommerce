package com.example.ProjectBE.dto.request.UserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@ToString
public class UserCreationRequest {
    String firstName;
    String userName;
    String passWord;
    String role;

    public UserCreationRequest(String firstName, String userName, String passWord, String role) {
        this.firstName = firstName;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

}
