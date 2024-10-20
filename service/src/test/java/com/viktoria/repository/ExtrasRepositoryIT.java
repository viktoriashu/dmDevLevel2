//package com.viktoria.repository;
//
//import com.viktoria.TestBase;
//import com.viktoria.database.repository.ExtrasRepository;
//import com.viktoria.database.entity.Extras;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class ExtrasRepositoryIT extends TestBase {
//
//    private final ExtrasRepository extrasRepository = new ExtrasRepository(session);
//
//    @Test
//    void checkSave() {
//        Extras extras = createExtras();
//
//        extrasRepository.save(extras);
//
//        Extras actualExtras = extrasRepository.findById(extras.getId()).get();
//        assertThat(actualExtras).isEqualTo(extras);
//    }
//
//    @Test
//    void checkDelete() {
//        Extras extras = createExtras();
//        extrasRepository.save(extras);
//
//        extrasRepository.delete(extras);
//
//        boolean actualExtras = extrasRepository.findById(extras.getId()).isEmpty();
//        assertThat(actualExtras).isNotEqualTo(extras);
//    }
//
//    @Test
//    void checkUpdate() {
//        Extras extras = createExtras();
//        extrasRepository.save(extras);
//        extras.setName("check");
//
//        extrasRepository.update(extras);
//
//        Extras actualExtras = extrasRepository.findById(extras.getId()).get();
//        assertThat(actualExtras.getName()).isEqualTo("check");
//    }
//
//    @Test
//    void checkFindAll() {
//        Extras extras = createExtras();
//        Extras extras2 = createExtras2();
//        extrasRepository.save(extras);
//        extrasRepository.save(extras2);
//
//        List<Extras> extrasList = extrasRepository.findAll();
//
//        assertThat(extrasList).contains(extras, extras2);
//    }
//
//    @Test
//    void checkFindById() {
//        Extras extras = createExtras();
//        extrasRepository.save(extras);
//
//        Extras actualExtras = extrasRepository.findById(extras.getId()).get();
//
//        assertThat(actualExtras).isEqualTo(extras);
//    }
//
//    private Extras createExtras() {
//        Extras extras = Extras.builder()
//                .name("TestExtra")
//                .description("TestDescription")
//                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
//                .build();
//        return extras;
//    }
//
//    private Extras createExtras2() {
//        Extras extras = Extras.builder()
//                .name("TestExtra1")
//                .description("TestDescription")
//                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
//                .build();
//        return extras;
//    }
//}
