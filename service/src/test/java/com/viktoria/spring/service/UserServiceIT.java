package com.viktoria.spring.service;

import com.viktoria.spring.IntegrationTestBase;
import com.viktoria.spring.database.entity.Role;
import com.viktoria.spring.dto.UserCreateEditDto;
import com.viktoria.spring.dto.UserFilter;
import com.viktoria.spring.dto.UserReadDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;


    @Test
    void create() {
        UserCreateEditDto userDto = CreateUser();
        UserReadDto actualResult = userService.create(userDto);

        assertThat(userDto.getFirstName()).isEqualTo(actualResult.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(actualResult.getLastName());
        assertThat(userDto.getLogin()).isEqualTo(actualResult.getLogin());

        //пароля в UserReadDto нет
//        Assertions.assertThat(userDto.getPassword()).isEqualTo(actualResult.get);
        assertThat(userDto.getPhoneNumber()).isEqualTo(actualResult.getPhoneNumber());
        assertThat(userDto.getRole()).isEqualTo(actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = CreateUser();

        Optional<UserReadDto> actualResult = userService.update(5L, userDto);

        actualResult.ifPresent(user -> {
            assertThat(userDto.getFirstName()).isEqualTo(actualResult.get().getFirstName());
            assertThat(userDto.getLastName()).isEqualTo(actualResult.get().getLastName());
            assertThat(userDto.getLogin()).isEqualTo(actualResult.get().getLogin());
            assertThat(userDto.getPhoneNumber()).isEqualTo(actualResult.get().getPhoneNumber());
            assertThat(userDto.getRole()).isEqualTo(actualResult.get().getRole());
        });
    }

    @Test
    void delete() {
        assertThat(userService.delete(21L)).isFalse();
        assertThat(userService.delete(1L)).isTrue();
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(5L);

        assertThat(maybeUser.get().getId()).isEqualTo(5L);
    }


    //тут есть вопросики
    @Test
    void findAll() {
        UserFilter filter = new UserFilter("", "", "89309302723");
        Pageable pageable = PageRequest.of(0, 20);
        Page<UserReadDto> result = userService.findAll(filter, pageable);

        assertThat(result).hasSize(1);
    }


    private static @NotNull UserCreateEditDto CreateUser() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "testFirstName",
                "testLastName",
                "testLogin",
                "testPassword",
                "testPhoneNumber",
                Role.USER
        );
        return userDto;
    }
}
