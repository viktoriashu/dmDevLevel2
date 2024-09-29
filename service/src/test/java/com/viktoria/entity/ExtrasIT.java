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

public class ExtrasIT {

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
    void checkCreateExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(extras);
        session.flush();
        session.clear();

        assertThat(extras.getId()).isNotNull();
    }

    @Test
    void checkUpdateExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(extras);
        session.flush();
        session.clear();

        extras = Extras.builder()
                .name("TestExtra1")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.saveOrUpdate(extras);
        session.flush();
        session.clear();

        assertThat(extras.getName()).contains("TestExtra1");
    }

    @Test
    void checkReadExtras() {
        Extras extras1 = Extras.builder()
                .name("TestExtra1")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(extras1);

        Extras extras2 = Extras.builder()
                .name("TestExtra2")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(extras2);

        List<Extras> extrasList = new ArrayList<>();
        extrasList.add(extras1);
        extrasList.add(extras2);
        session.flush();
        session.clear();

        assertThat(extrasList.size()).isEqualTo(2);
    }


    @Test
    void checkDeleteExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(extras);
        session.delete(extras);
        session.flush();
        session.clear();

        assertThat(session.get(Extras.class, extras.getId())).isNull();
    }
}
