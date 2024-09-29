package com.viktoria.entity;

import com.viktoria.util.HibernateTestUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimIT {

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
    void checkCreateClaim() {
        User user1 = getUser1();
        User user2 = getUser2();
        Sup sup = getSup();
        Extras extras = getExtras();

        Claim claim = Claim.builder()
                .client(user1)
                .admin(user2)
                .sup(sup)
                .extras(extras)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim);
        session.flush();
        session.clear();

        assertThat(claim.getId()).isNotNull();
    }

    @Test
    void checkUpdateClaim() {
        User user1 = getUser1();
        User user2 = getUser2();
        Sup sup = getSup();
        Extras extras = getExtras();

        Claim claim = Claim.builder()
                .client(user1)
                .admin(user2)
                .sup(sup)
                .extras(extras)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim);

        claim = Claim.builder()
                .client(user1)
                .admin(user2)
                .sup(sup)
                .extras(extras)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(5)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200))
                .build();
        session.saveOrUpdate(claim);
        session.flush();
        session.clear();

        assertThat(claim.getDurationRent()).isEqualTo(5);
    }

    @Test
    void checkReadClaim() {
        User user1 = getUser1();
        User user2 = getUser2();
        Sup sup = getSup();
        Extras extras = getExtras();

        Claim claim1 = Claim.builder()
                .client(user1)
                .admin(user2)
                .sup(sup)
                .extras(extras)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim1);

        Claim claim2 = Claim.builder()
                .client(user1)
                .admin(user2)
                .sup(sup)
                .extras(extras)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim2);

        List<Claim> claims = new ArrayList<>();
        claims.add(claim1);
        claims.add(claim2);
        session.flush();
        session.clear();

        assertThat(claims.size()).isEqualTo(2);
    }

    @Test
    void checkDeleteClaim() {
        User user1 = getUser1();
        User user2 = getUser2();
        Sup sup = getSup();
        Extras extras = getExtras();

        Claim claim = Claim.builder()
                .client(user1)
                .admin(user2)
                .sup(sup)
                .extras(extras)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim);
        session.delete(claim);
        session.flush();
        session.clear();

        assertThat(session.get(Claim.class, claim.getId())).isNull();
    }

    private User getUser1() {
        User user1 = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin4")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.USER)
                .build();
        session.save(user1);
        return user1;
    }

    private User getUser2() {
        User user2 = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin5")
                .password("TestPassword")
                .phoneNumber("TestNumber")
                .role(Role.ADMIN)
                .build();
        session.save(user2);
        return user2;
    }

    private Sup getSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);
        return sup;
    }

    private Extras getExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(extras);
        return extras;
    }
}
