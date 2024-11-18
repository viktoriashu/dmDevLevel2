package com.viktoria.spring.mapper.user;

import com.viktoria.spring.database.entity.User;
import com.viktoria.spring.dto.user.UserCreateEditDto;
import com.viktoria.spring.mapper.Mapper;
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
        if (object.getPassword() != null) {
            user.setPassword(object.getPassword());
        }
        user.setPhoneNumber(object.getPhoneNumber());
        user.setRole(object.getRole());
    }
}
