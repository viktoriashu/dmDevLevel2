package com.viktoria.spring.dto;

import com.viktoria.spring.database.entity.Role;
import lombok.Value;

@Value
public class UserUpdateDto {
    Long id;
    String firstName;
    String lastName;
    String password;
    String phoneNumber;
    Role role;
}
