package com.viktoria;

import com.viktoria.config.ApplicationConfiguration;
import com.viktoria.util.HibernateTestUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public abstract class TestBase {


    protected static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    private static SessionFactory sessionFactory;
    protected static EntityManager entityManager;

    @BeforeAll
    static void createSessionFactory() {
        sessionFactory = ctx.getBean(SessionFactory.class);
        entityManager = ctx.getBean(EntityManager.class);
    }


    @AfterAll
    static void closeSessionFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void openSession() {
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void closeSession() {
        entityManager.getTransaction().rollback();
    }



//это работало, пока я сюда не полезла
//    private static SessionFactory sessionFactory;
//    protected static Session session;
//
//    @BeforeAll
//    static void createSessionFactory() {
//        sessionFactory = HibernateTestUtil.buildSessionFactory();
//        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[] { Session.class },
//                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));
//    }
//
//    @AfterAll
//    static void closeSessionFactory() {
//        sessionFactory.close();
//    }
//
//    @BeforeEach
//    void openSession() {
//        session.getTransaction().begin();
//    }
//
//    @AfterEach
//    void closeSession() {
//        session.getTransaction().rollback();
//    }
}
