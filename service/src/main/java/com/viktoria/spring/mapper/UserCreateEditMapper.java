package com.viktoria.spring.mapper;

import com.viktoria.spring.database.entity.User;
import com.viktoria.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setLogin(object.getLogin());
        user.setPassword(object.getPassword());
        user.setPhoneNumber(object.getPhoneNumber());
        user.setRole(object.getRole());
    }
}
