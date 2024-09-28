package com.viktoria;

import com.viktoria.entity.Role;
import com.viktoria.entity.User;
import com.viktoria.util.HibernateTestUtil;
import org.junit.jupiter.api.Test;

public class HibernateRunnerTest {

    @Test
    void checkDocker() {
        try (var sessionFactory = HibernateTestUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .firstName("TestName")
                    .lastName("TestLastName")
                    .login("TestLogin")
                    .password("TestPassword")
                    .phoneNumber("TestNumber")
                    .role(Role.USER)
                    .build();
            session.save(user);

            session.getTransaction().commit();
        }
    }
}
