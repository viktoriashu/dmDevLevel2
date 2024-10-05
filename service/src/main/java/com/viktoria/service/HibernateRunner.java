package com.viktoria.service;

import com.viktoria.entity.ExtrasClaim;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;


import com.viktoria.entity.Claim;
import com.viktoria.entity.Extras;
import com.viktoria.entity.Role;
import com.viktoria.entity.Status;
import com.viktoria.entity.Sup;
import com.viktoria.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {


        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User client = User.builder()
                    .firstName("TestName")
                    .lastName("TestLastName")
                    .login("TestLogin4")
                    .password("TestPassword")
                    .phoneNumber("TestNumber")
                    .role(Role.USER)
                    .build();

            session.saveOrUpdate(client);

            User admin = User.builder()
                    .firstName("TestName")
                    .lastName("TestLastName")
                    .login("TestLogin5")
                    .password("TestPassword")
                    .phoneNumber("TestNumber")
                    .role(Role.ADMIN)
                    .build();

            session.saveOrUpdate(admin);

            Sup sup = Sup.builder()
                    .model("TestModel")
                    .numberSeats(1)
                    .description("TestDescription")
                    .price(BigDecimal.valueOf(100))
                    .build();

            session.saveOrUpdate(sup);


            Extras extras = Extras.builder()
                    .name("TestExtra")
                    .description("TestDescription")
                    .price(BigDecimal.valueOf(100))
                    .build();

            session.saveOrUpdate(extras);


            Claim claim = Claim.builder()
                    .client(client)
                    .admin(admin)
                    .sup(sup)
                    .dataStartRent(LocalDate.of(2024, 12, 15))
                    .durationRent(2)
                    .status(Status.OPEN)
                    .price(BigDecimal.valueOf(1200))
                    .build();

            session.saveOrUpdate(claim);

            ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);

            session.saveOrUpdate(extrasClaim);


//            extrasClaim.setExtras(extras);
//            extrasClaim.setClaim(claim);


            session.getTransaction().commit();
        }
    }
}
