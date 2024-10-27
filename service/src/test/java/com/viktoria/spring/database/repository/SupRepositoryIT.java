package com.viktoria.spring.database.repository;

import com.viktoria.spring.IntegrationTestBase;
import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.dto.SupFilter;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class SupRepositoryIT extends IntegrationTestBase {

    private final SupRepository supRepository;
    private final EntityManager entityManager;

    @Test
    void checkSave() {
        Sup sup1 = createSup1();

        supRepository.save(sup1);

        Sup actualSup = supRepository.findById(sup1.getId()).get();
        assertThat(actualSup).isEqualTo(sup1);
    }

    @Test
    void checkDelete() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);

        supRepository.delete(sup1);

        boolean actualSup = supRepository.findById(sup1.getId()).isEmpty();
        assertThat(actualSup).isNotEqualTo(sup1);
    }

    @Test
    void checkUpdate() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        sup1.setModel("check");

        supRepository.update(sup1);

        Sup actualSup = supRepository.findById(sup1.getId()).get();
        assertThat(actualSup.getModel()).isEqualTo("check");
    }

    @Test
    void checkFindAll() {
        Sup sup1 = createSup1();
        Sup sup2 = createSup2();
        supRepository.save(sup1);
        supRepository.save(sup2);

        List<Sup> sups = supRepository.findAll();

        assertThat(sups).contains(sup1, sup2);
    }

    @Test
    void checkFindById() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);

        Sup actualSup = supRepository.findById(sup1.getId()).get();

        assertThat(actualSup).isEqualTo(sup1);
    }

    @Test
    void checkSizeFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();

        List<Sup> modelSup = supRepository.findBySupFilterQuerydsl(filter);

        assertThat(modelSup).hasSize(2);
    }

    @Test
    void checkSortFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();
        List<Sup> modelSup = supRepository.findBySupFilterQuerydsl(filter);

        List<BigDecimal> prices = modelSup.stream().map(Sup::getPrice).collect(toList());

        assertThat(prices).contains(BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void checkModelFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();
        List<Sup> modelSup = supRepository.findBySupFilterQuerydsl(filter);

        List<String> model = modelSup.stream().map(Sup::getModel).collect(toList());

        assertThat(model).containsOnly("TestModel");
    }

    @Test
    void checkNumSeatsFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();
        List<Sup> modelSup = supRepository.findBySupFilterQuerydsl(filter);

        List<Integer> model = modelSup.stream().map(Sup::getNumberSeats).collect(toList());

        assertThat(model).containsOnly(1);
    }

    @Test
    void checkSizeFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();

        List<Sup> modelSup = supRepository.findBySupFilterCriteriaApi(filter);

        assertThat(modelSup).hasSize(2);
    }

    @Test
    void checkSortFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();
        List<Sup> modelSup = supRepository.findBySupFilterCriteriaApi(filter);

        List<BigDecimal> prices = modelSup.stream().map(Sup::getPrice).collect(toList());

        assertThat(prices).contains(BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void checkModelFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();
        List<Sup> modelSup = supRepository.findBySupFilterCriteriaApi(filter);

        List<String> model = modelSup.stream().map(Sup::getModel).collect(toList());

        assertThat(model).containsOnly("TestModel");
    }

    @Test
    void checkNumSeatsFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        supRepository.save(sup1);
        Sup sup2 = createSup2();
        supRepository.save(sup2);
        Sup sup3 = createSup3();
        supRepository.save(sup3);
        Sup sup4 = createSup4();
        supRepository.save(sup4);
        SupFilter filter = createSupFilter();
        entityManager.flush();
        entityManager.clear();
        List<Sup> modelSup = supRepository.findBySupFilterCriteriaApi(filter);

        List<Integer> model = modelSup.stream().map(Sup::getNumberSeats).collect(toList());

        assertThat(model).containsOnly(1);
    }


    private static Sup createSup1() {
        Sup sup1 = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        return sup1;
    }

    private static Sup createSup2() {
        Sup sup2 = Sup.builder()
                .model("TestModel")
                .numberSeats(2)
                .description("TestDescription")
                .price(BigDecimal.valueOf(500))
                .build();
        return sup2;
    }

    private static Sup createSup3() {
        Sup sup3 = Sup.builder()
                .model("FAKE")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(70))
                .build();
        return sup3;
    }

    private static Sup createSup4() {
        Sup sup4 = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(70))
                .build();
        return sup4;
    }

    private static SupFilter createSupFilter() {
        SupFilter filter = SupFilter.builder()
                .model("TestModel")
                .numberSeats(1)
                .build();
        return filter;
    }

}
