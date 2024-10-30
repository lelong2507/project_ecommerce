package com.example.ProjectBE.dto.request.UserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class UserUpdateRequest {
    String userName;
    String passWord;

}
