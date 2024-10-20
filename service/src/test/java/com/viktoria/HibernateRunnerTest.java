package com.viktoria;

import com.viktoria.database.entity.Role;
import com.viktoria.database.entity.User;
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
