package com.viktoria.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;


import com.viktoria.entity.Claim;
import com.viktoria.entity.Role;
import com.viktoria.entity.Status;
import com.viktoria.entity.StatusSup;
import com.viktoria.entity.Sup;
import com.viktoria.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {


        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = User.builder()
                    .firstName("TestName")
                    .lastName("TestLastName")
                    .login("TestLogin")
                    .password("TestPassword")
                    .number("TestNumber")
                    .role(Role.USER)
                    .build();

            session.saveOrUpdate(user);

            Sup sup = Sup.builder()
                    .model("TestModel")
                    .build();

            session.saveOrUpdate(sup);

            Claim claim = Claim.builder()
                    .client(user)
                    .admin(user)
                    .sup(sup)
                    .dataStartRent(LocalDate.of(2024, 12, 15))
                    .statusSup(StatusSup.FREE)
                    .durationRent(2)
                    .status(Status.OPEN)
                    .price(BigDecimal.valueOf(1200))
                    .build();

            session.saveOrUpdate(claim);

            session.getTransaction().commit();
        }
    }
}
