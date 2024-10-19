package com.viktoria;

import com.viktoria.util.HibernateTestUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class TestBase {

    private static SessionFactory sessionFactory;
    protected static Session session;

    @BeforeAll
    static void createSessionFactory() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[] { Session.class },
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));
    }

    @AfterAll
    static void closeSessionFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void openSession() {
        session.getTransaction().begin();
    }

    @AfterEach
    void closeSession() {
        session.getTransaction().rollback();
    }
}
