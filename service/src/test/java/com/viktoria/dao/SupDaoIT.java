package com.viktoria.dao;

import com.viktoria.TestBase;
import com.viktoria.dto.SupFilter;
import com.viktoria.entity.Sup;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;


class SupDaoIT extends TestBase {

    private final SupDao supDao = SupDao.getInstance();

    @Test
    void checkSizeFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();

        List<Sup> modelSup = supDao.findBySupFilterQuerydsl(session, filter);

        assertThat(modelSup).hasSize(2);
    }

    @Test
    void checkSortFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();
        List<Sup> modelSup = supDao.findBySupFilterQuerydsl(session, filter);

        List<BigDecimal> prices = modelSup.stream().map(Sup::getPrice).collect(toList());

        assertThat(prices).contains(BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void checkModelFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();
        List<Sup> modelSup = supDao.findBySupFilterQuerydsl(session, filter);

        List<String> model = modelSup.stream().map(Sup::getModel).collect(toList());

        assertThat(model).containsOnly("TestModel");
    }

    @Test
    void checkNumSeatsFindBySupFilterQuerydsl() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();
        List<Sup> modelSup = supDao.findBySupFilterQuerydsl(session, filter);

        List<Integer> model = modelSup.stream().map(Sup::getNumberSeats).collect(toList());

        assertThat(model).containsOnly(1);
    }

    @Test
    void checkSizeFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();

        List<Sup> modelSup = supDao.findBySupFilterCriteriaApi(session, filter);

        assertThat(modelSup).hasSize(2);
    }

    @Test
    void checkSortFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();
        List<Sup> modelSup = supDao.findBySupFilterCriteriaApi(session, filter);

        List<BigDecimal> prices = modelSup.stream().map(Sup::getPrice).collect(toList());

        assertThat(prices).contains(BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void checkModelFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();
        List<Sup> modelSup = supDao.findBySupFilterCriteriaApi(session, filter);

        List<String> model = modelSup.stream().map(Sup::getModel).collect(toList());

        assertThat(model).containsOnly("TestModel");
    }

    @Test
    void checkNumSeatsFindBySupFilterCriteriaApi() {
        Sup sup1 = createSup1();
        session.save(sup1);
        Sup sup2 = createSup2();
        session.save(sup2);
        Sup sup3 = createSup3();
        session.save(sup3);
        Sup sup4 = createSup4();
        session.save(sup4);
        SupFilter filter = createSupFilter();
        session.flush();
        session.clear();
        List<Sup> modelSup = supDao.findBySupFilterCriteriaApi(session, filter);

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
