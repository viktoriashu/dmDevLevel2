package com.viktoria.spring.mapper;

import com.viktoria.spring.database.entity.User;
import com.viktoria.spring.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                object.getLogin(),
                object.getPhoneNumber(),
                object.getRole()
        );
    }
}
