package com.viktoria.spring.mapper;

import com.viktoria.spring.database.entity.User;
import com.viktoria.spring.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateMapper implements Mapper<User, UserUpdateDto> {

    @Override
    public UserUpdateDto map(User object) {
        return new UserUpdateDto(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                object.getPassword(),
                object.getPhoneNumber(),
                object.getRole()
        );
    }
}
