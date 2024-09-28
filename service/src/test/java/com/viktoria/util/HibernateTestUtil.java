package com.viktoria.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateTestUtil {
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

    static {
        postgres.start();
    }

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = HibernateUtil.buildConfiguration();
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
