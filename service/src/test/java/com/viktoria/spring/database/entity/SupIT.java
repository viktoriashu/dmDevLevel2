package com.viktoria.spring.database.entity;

import com.viktoria.spring.IntegrationTestBase;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class SupIT extends IntegrationTestBase {

    private final EntityManager entityManager;

    @Test
    void checkCreateSup() {
        Map<String, Object> properties = Map.of(GraphSemantic.FETCH.getJpaHintName(),
                entityManager.getEntityGraph("WithClaim"));
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();

        entityManager.persist(sup);

        entityManager.flush();
        entityManager.clear();
        Sup actualSup = entityManager.find(Sup.class, sup.getId(), properties);
        assertThat(actualSup.getId()).isEqualTo(sup.getId());
    }

    @Test
    void checkUpdateSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(sup);
        sup.setModel("TestModel1");

        entityManager.merge(sup);

        entityManager.flush();
        entityManager.clear();
        Sup actualSup = entityManager.find(Sup.class, sup.getId());
        assertThat(actualSup.getModel()).isEqualTo(sup.getModel());
    }

    @Test
    void checkReadSup() {
        Sup sup = Sup.builder()
                .model("TestModel1")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(sup);
        entityManager.flush();
        entityManager.clear();

        Sup actualSup = entityManager.find(Sup.class, sup.getId());

        assertThat(actualSup).isEqualTo(sup);
    }

    @Test
    void checkDeleteSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(sup);

        entityManager.remove(sup);

        entityManager.flush();
        entityManager.clear();
        Sup actualSup = entityManager.find(Sup.class, sup.getId());
        assertThat(actualSup).isNull();
    }
}