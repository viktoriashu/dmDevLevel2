package com.viktoria.spring.database.entity;

import com.viktoria.spring.IntegrationTestBase;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserIT extends IntegrationTestBase {

    private final EntityManager entityManager;

    @Test
    void checkCreateUser() {
        User user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();

        entityManager.persist(user);

        entityManager.flush();
        entityManager.clear();
        User actualUser = entityManager.find(User.class, user.getId());
        assertThat(actualUser.getId()).isEqualTo(user.getId());
    }

    @Test
    void checkUpdateUser() {
        User user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        entityManager.persist(user);
        user.setPhoneNumber("TestNumber1");

        entityManager.merge(user);

        entityManager.flush();
        entityManager.clear();
        User actualUser = entityManager.find(User.class, user.getId());
        assertThat(actualUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    void checkReadUser() {
        User user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin1")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        entityManager.persist(user);
        entityManager.flush();
        entityManager.clear();

        User actualUser = entityManager.find(User.class, user.getId());

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void checkDeleteUser() {
        User user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        entityManager.persist(user);

        entityManager.remove(user);

        entityManager.flush();
        entityManager.clear();
        User actualUser = entityManager.find(User.class, user.getId());
        assertThat(actualUser).isNull();
    }
}
