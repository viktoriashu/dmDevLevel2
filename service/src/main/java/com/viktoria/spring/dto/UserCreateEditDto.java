package com.viktoria.spring.dto;

import com.viktoria.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String firstName;
    String lastName;
    String login;
    String password;
    String phoneNumber;
    Role role;
}
