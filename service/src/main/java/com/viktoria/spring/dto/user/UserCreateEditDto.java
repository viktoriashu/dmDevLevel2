package com.viktoria.spring.dto.user;

import com.viktoria.spring.database.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String login;

    @NotBlank
    @Size(min = 6)
    String password;

    @Pattern(regexp = "^(\\+7)([0-9]{10})$")
    String phoneNumber;

    Role role;
}
