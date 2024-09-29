package com.viktoria.entity;

import com.viktoria.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIT {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    static void createSessionFactory() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
    }

    @AfterAll
    static void closeSessionFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void openSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void closeSession() {
        session.getTransaction().rollback();
        session.close();
    }


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
        session.save(user);
        session.flush();
        session.clear();

        assertThat(user.getId()).isNotNull();
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
        session.save(user);


        user = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin")
                .password("TestPassword")
                .phoneNumber("TestNumber1")
                .role(Role.USER)
                .build();
        session.saveOrUpdate(user);
        session.flush();
        session.clear();

        assertThat(user.getPhoneNumber()).contains("TestNumber1");
    }

    @Test
    void checkReadUser() {
        User user1 = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin1")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        session.save(user1);

        User user2 = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin2")
                .password("TestPassword")
                .phoneNumber("TestNumber1")
                .role(Role.USER)
                .build();
        session.save(user2);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        session.flush();
        session.clear();

        assertThat(users.size()).isEqualTo(2);
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
        session.save(user);
        session.delete(user);
        session.flush();
        session.clear();

        assertThat(session.get(User.class, user.getId())).isNull();
    }
}
