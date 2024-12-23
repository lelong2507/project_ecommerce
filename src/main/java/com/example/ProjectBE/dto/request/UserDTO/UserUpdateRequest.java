package com.example.ProjectBE.dto.request.UserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@ToString
public class UserUpdateRequest {
    String firstName;
    String lastName;
    String address;
    String avatar;
    String phoneNumber;
    Date userDob;
}
