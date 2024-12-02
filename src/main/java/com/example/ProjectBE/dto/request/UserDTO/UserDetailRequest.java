package com.example.ProjectBE.dto.request.UserDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailRequest {
    String userName;
    String email;
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    Date userDob;
    String avatar;
}
