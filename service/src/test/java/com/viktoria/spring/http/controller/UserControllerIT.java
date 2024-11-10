package com.viktoria.spring.http.controller;

import com.viktoria.spring.IntegrationTestBase;
import com.viktoria.spring.database.entity.Role;
import com.viktoria.spring.dto.UserCreateEditDto;
import com.viktoria.spring.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsArrayWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.thymeleaf.spring6.expression.Fields;

import static com.viktoria.spring.dto.UserCreateEditDto.Fields.firstName;
import static com.viktoria.spring.dto.UserCreateEditDto.Fields.lastName;
import static com.viktoria.spring.dto.UserCreateEditDto.Fields.login;
import static com.viktoria.spring.dto.UserCreateEditDto.Fields.password;
import static com.viktoria.spring.dto.UserCreateEditDto.Fields.phoneNumber;
import static com.viktoria.spring.dto.UserCreateEditDto.Fields.role;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserControllerIT extends IntegrationTestBase {

    private final MockMvc mvc;

    //и тут вопрос остался
    @Test
    void findAll() throws Exception {
        mvc.perform(get("/users")
                )
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/users"),
                        model().attributeExists("users")
                );
//                .andExpect(model().attribute("users", IsArrayWithSize.arrayWithSize(4)));
    }

    @Test
    void findById() throws Exception {
        mvc.perform(get("/users/" + 1)
                )
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/user"),
                        model().attributeExists("user"),
                        model().attribute("roles", Role.values())
                );
    }

    @Test
    void registration() throws Exception {
        mvc.perform(get("/users/registration"))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/registration"),
                        model().attributeExists("user"),
                        model().attribute("roles", Role.values())
                );
    }


//после изменения UserCreateEditMapper теперь этот валится
    @Test
    void create() throws Exception {
        mvc.perform(post("/users")
                        .param(firstName, "testFirstName")
                        .param(lastName, "testLastName")
                        .param(login, "testLogin")
                        .param(password, "testPassword")
                        .param(phoneNumber, "testPhoneNumber")
                        .param(role, "USER")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    @Test
    void delete() throws Exception {
        mvc.perform(post("/users/4/delete")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/users")
                );
    }

    @Test
    void update() throws Exception {
        mvc.perform(post("/users/1/update")
                        .param(firstName, "Олег")
                        .param(lastName, "Дынькин")
                        .param(login, "old")
                        .param(password, "old")
                        .param(phoneNumber, "89208947820")
                        .param(role, "ADMIN")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/*")
                );
    }

}