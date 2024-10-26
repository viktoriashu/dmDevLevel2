package com.viktoria.entity;

import com.viktoria.TestBase;
import com.viktoria.database.entity.Extras;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtrasIT extends TestBase {

    @Test
    void checkCreateExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();

        entityManager.persist(extras);

        entityManager.flush();
        entityManager.clear();
        Extras actualExtras = entityManager.find(Extras.class, extras.getId());
        assertThat(actualExtras.getId()).isEqualTo(extras.getId());
    }

    @Test
    void checkUpdateExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(extras);
        entityManager.flush();
        entityManager.clear();
        extras.setName("TestExtra1");

        entityManager.merge(extras);

        entityManager.flush();
        entityManager.clear();
        Extras actualExtras = entityManager.find(Extras.class, extras.getId());
        assertThat(actualExtras.getName()).isEqualTo(extras.getName());
    }

    @Test
    void checkReadExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra1")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(extras);
        entityManager.flush();
        entityManager.clear();

        Extras actualExtras = entityManager.find(Extras.class, extras.getId());

        assertThat(actualExtras).isEqualTo(extras);
    }

    @Test
    void checkDeleteExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(extras);

        entityManager.remove(extras);

        entityManager.flush();
        entityManager.clear();
        Extras actualExtras = entityManager.find(Extras.class, extras.getId());
        assertThat(actualExtras).isNull();
    }

}
