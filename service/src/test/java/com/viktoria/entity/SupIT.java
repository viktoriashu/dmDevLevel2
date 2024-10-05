package com.viktoria.entity;

import com.viktoria.TestBase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SupIT extends TestBase {

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

        Sup actualSup = session.find(Sup.class, sup.getId());

        assertThat(actualSup.getId()).isEqualTo(sup.getId());
    }

    @Test
    void checkUpdateSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);
        sup.setModel("TestModel1");
        session.update(sup);
        session.flush();
        session.clear();

        Sup actualSup = session.find(Sup.class, sup.getId());

        assertThat(actualSup.getModel()).isEqualTo(sup.getModel());
    }

    @Test
    void checkReadSup() {
        Sup sup = Sup.builder()
                .model("TestModel1")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);
        session.flush();
        session.clear();

        Sup actualSup = session.find(Sup.class, sup.getId());

        assertThat(actualSup).isEqualTo(sup);
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

        Sup actualSup = session.find(Sup.class, sup.getId());

        assertThat(actualSup).isNull();
    }
}


