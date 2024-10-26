package com.viktoria.repository;

import com.viktoria.TestBase;
import com.viktoria.database.entity.Role;
import com.viktoria.database.entity.User;
import com.viktoria.database.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryIT extends TestBase {

    private final UserRepository userRepository = ctx.getBean(UserRepository.class);

    @Test
    void checkSave() {
    User user = createUser();
        userRepository.save(user);

        User actualUser = userRepository.findById(user.getId()).get();

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void checkDelete() {
        User user = createUser();
        userRepository.save(user);

        userRepository.delete(user);

        boolean actualUser = userRepository.findById(user.getId()).isEmpty();
        assertThat(actualUser).isNotEqualTo(user);
    }

    @Test
    void checkUpdate() {
        User user = createUser();
        userRepository.save(user);
        user.setFirstName("check");

        userRepository.update(user);

        User actualUser = userRepository.findById(user.getId()).get();
        assertThat(actualUser.getFirstName()).isEqualTo("check");
    }

    @Test
    void checkFindAll() {
        User user1 = createUser();
        User user2 = createUser2();
        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertThat(users).contains(user1, user2);
    }

    @Test
    void checkFindById() {
        User user = createUser();
        userRepository.save(user);

        User actualUser = userRepository.findById(user.getId()).get();

        assertThat(actualUser).isEqualTo(user);
    }

    private User createUser() {
        User user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin4")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        return user;
    }

    private User createUser2() {
        User user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin1")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        return user;
    }
}
