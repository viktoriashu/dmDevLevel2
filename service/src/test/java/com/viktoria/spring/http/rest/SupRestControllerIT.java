package com.viktoria.spring.http.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktoria.spring.IntegrationTestBase;
import com.viktoria.spring.dto.sup.SupCreateEditDto;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class SupRestControllerIT extends IntegrationTestBase {

    private final MockMvc mvc;
    private final ObjectMapper mapper;


//вообще не работает

//    private JSONObject jsonObject = new JSONObject();
//    private final SupCreateEditDto supCreateEditDto;


    //не работает. Может сюда нужно DTO засунуть в JSON преобразовать?
    @Test
    void create() throws Exception {
        mvc.perform(post("/api/v1/sups")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                //тут какое-то тестирование на multipart
                .content("""
                         {
                               "model": "test",
                               "numberSeats": "test",
                               "description": "test",
                               "image": "test",
                               "price": "0.0"
                         }
                        """)
        ).andExpectAll(status().isCreated(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void create2() throws Exception {
        SupCreateEditDto supCreateEditDto = createSupCreateEditDto();
        String json = mapper.writeValueAsString(supCreateEditDto);


        mvc.perform(post("/api/v1/sups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpectAll(status().isCreated(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void findAll() throws Exception {
        mvc.perform(get("/api/v1/sups")
                )
                .andExpectAll(
                        status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
//                        MockMvcResultMatchers.content().json("""
//                                [{"id":2,"model":"testsupvalid","numberSeats":2,"description":"testsupvalid","image":"Gladiator Pro 11'6.webp","price":500.00}]
//                                """)
                );
    }

    private static SupCreateEditDto createSupCreateEditDto() {
        return SupCreateEditDto.builder()
                .model("Test Model")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(0.0))
                .build();
    }
}
