package com.viktoria.spring.dto;

import com.viktoria.spring.database.entity.Role;
import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String firstName;
    String lastName;
    String login;
    String phoneNumber;
    Role role;
}
