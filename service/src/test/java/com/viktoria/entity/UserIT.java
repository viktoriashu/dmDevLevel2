//package com.viktoria.entity;
//
//import com.viktoria.TestBase;
//import com.viktoria.database.entity.Role;
//import com.viktoria.database.entity.User;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class UserIT extends TestBase {
//
//    @Test
//    void checkCreateUser() {
//        User user = User.builder()
//                .firstName("TestName")
//                .lastName("TestLastName")
//                .login("TestLogin")
//                .password("TestPassword")
//                .phoneNumber("TestNumber")
//                .role(Role.USER)
//                .build();
//        session.save(user);
//        session.flush();
//        session.clear();
//
//        User actualUser = session.find(User.class, user.getId());
//
//        assertThat(actualUser.getId()).isEqualTo(user.getId());
//    }
//
//    @Test
//    void checkUpdateUser() {
//        User user = User.builder()
//                .firstName("TestName")
//                .lastName("TestLastName")
//                .login("TestLogin")
//                .password("TestPassword")
//                .phoneNumber("TestNumber")
//                .role(Role.USER)
//                .build();
//        session.save(user);
//        user.setPhoneNumber("TestNumber1");
//        session.update(user);
//        session.flush();
//        session.clear();
//
//        User actualUser = session.find(User.class, user.getId());
//
//        assertThat(actualUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
//    }
//
//    @Test
//    void checkReadUser() {
//        User user = User.builder()
//                .firstName("TestName")
//                .lastName("TestLastName")
//                .login("TestLogin1")
//                .password("TestPassword")
//                .phoneNumber("TestNumber")
//                .role(Role.USER)
//                .build();
//        session.save(user);
//        session.flush();
//        session.clear();
//
//        User actualUser = session.find(User.class, user.getId());
//
//        assertThat(actualUser).isEqualTo(user);
//    }
//
//    @Test
//    void checkDeleteUser() {
//        User user = User.builder()
//                .firstName("TestName")
//                .lastName("TestLastName")
//                .login("TestLogin")
//                .password("TestPassword")
//                .phoneNumber("TestNumber")
//                .role(Role.USER)
//                .build();
//        session.save(user);
//        session.delete(user);
//        session.flush();
//        session.clear();
//
//        User actualUser = session.find(User.class, user.getId());
//
//        assertThat(actualUser).isNull();
//    }
//}
