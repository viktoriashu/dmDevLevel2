package com.viktoria.service;

import com.viktoria.database.repository.UserRepository;
import com.viktoria.util.HibernateUtil;
import java.sql.SQLException;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {



        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            var session = (Session)Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
//                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
            var session = sessionFactory.getCurrentSession();

            session.beginTransaction();

//            var userRepository = new UserRepository(session);

//            userRepository.save(User.builder()
//                    .firstName("TestName")
//                    .lastName("TestLastName")
//                    .login("TestLogin1")
//                    .password("TestPassword")
//                    .phoneNumber("TestNumber")
//                    .role(Role.USER)
//                    .build());

//            userRepository.findById(2L).ifPresent(System.out::println);
//
//            session.getTransaction().commit();

        }

//
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//
//            session.beginTransaction();
//
//            User client = User.builder()
//                    .firstName("TestName")
//                    .lastName("TestLastName")
//                    .login("TestLogin4")
//                    .password("TestPassword")
//                    .phoneNumber("TestNumber")
//                    .role(Role.USER)
//                    .build();
//
//            session.saveOrUpdate(client);
//
//            User admin = User.builder()
//                    .firstName("TestName")
//                    .lastName("TestLastName")
//                    .login("TestLogin5")
//                    .password("TestPassword")
//                    .phoneNumber("TestNumber")
//                    .role(Role.ADMIN)
//                    .build();
//
//            session.saveOrUpdate(admin);
//
//            Sup sup = Sup.builder()
//                    .model("TestModel")
//                    .numberSeats(1)
//                    .description("TestDescription")
//                    .price(BigDecimal.valueOf(100))
//                    .build();
//
//            session.saveOrUpdate(sup);
//
//
//            Extras extras = Extras.builder()
//                    .name("TestExtra")
//                    .description("TestDescription")
//                    .price(BigDecimal.valueOf(100))
//                    .build();
//
//            session.saveOrUpdate(extras);
//
//
//            Claim claim = Claim.builder()
//                    .client(client)
//                    .admin(admin)
//                    .sup(sup)
//                    .dataStartRent(LocalDate.of(2024, 12, 15))
//                    .durationRent(2)
//                    .status(Status.OPEN)
//                    .price(BigDecimal.valueOf(1200))
//                    .build();
//
//            session.saveOrUpdate(claim);
//
//            ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
//
//            session.saveOrUpdate(extrasClaim);
//
//
////            extrasClaim.setExtras(extras);
////            extrasClaim.setClaim(claim);
//
//
//            session.getTransaction().commit();
//        }
    }
}
