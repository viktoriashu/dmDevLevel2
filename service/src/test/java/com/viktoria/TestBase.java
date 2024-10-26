package com.viktoria;

import com.viktoria.config.ApplicationConfigurationIT;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class TestBase {

    protected static AnnotationConfigApplicationContext ctx;
    protected EntityManager entityManager;

    @BeforeAll
    static void createApplicationContext() {
        ctx = new AnnotationConfigApplicationContext(ApplicationConfigurationIT.class);
    }

    @AfterAll
    static void closeApplicationContext() {
        ctx.close();
    }

    @BeforeEach
    void openEntityManager() {
        entityManager = ctx.getBean(EntityManager.class);
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void closeEntityManager() {
        entityManager.getTransaction().rollback();
    }
}
