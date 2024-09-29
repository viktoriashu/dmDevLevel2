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

import static org.assertj.core.api.Assertions.assertThat;

public class SupIT {

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
    void checkCreateSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);
        session.flush();
        session.clear();

        assertThat(sup.getId()).isNotNull();
    }

    @Test
    void checkUpdateUser() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);

        sup = Sup.builder()
                .model("TestModel1")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.saveOrUpdate(sup);
        session.flush();
        session.clear();

        assertThat(sup.getModel()).contains("TestModel1");
    }

    @Test
    void checkReadUser() {
        Sup sup1 = Sup.builder()
                .model("TestModel1")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup1);

        Sup sup2 = Sup.builder()
                .model("TestModel2")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup2);

        List<Sup> sups = new ArrayList<>();
        sups.add(sup1);
        sups.add(sup2);
        session.flush();
        session.clear();

        assertThat(sups.size()).isEqualTo(2);
    }

    @Test
    void checkDeleteSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);
        session.delete(sup);
        session.flush();
        session.clear();

        assertThat(session.get(Sup.class, sup.getId())).isNull();
    }
}


