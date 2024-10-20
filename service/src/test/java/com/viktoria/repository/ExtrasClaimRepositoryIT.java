//package com.viktoria.repository;
//
//import com.viktoria.TestBase;
//import com.viktoria.database.repository.ExtrasClaimRepository;
//import com.viktoria.database.entity.Claim;
//import com.viktoria.database.entity.Extras;
//import com.viktoria.database.entity.ExtrasClaim;
//import com.viktoria.database.entity.Role;
//import com.viktoria.database.entity.Status;
//import com.viktoria.database.entity.Sup;
//import com.viktoria.database.entity.User;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDate;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class ExtrasClaimRepositoryIT extends TestBase {
//
//    private final ExtrasClaimRepository extrasClaimRepository = new ExtrasClaimRepository(session);
//
//    @Test
//    void checkSave() {
//        ExtrasClaim extrasClaim = createExtrasClaim();
//
//        extrasClaimRepository.save(extrasClaim);
//
//        ExtrasClaim actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).get();
//        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
//    }
//
//    @Test
//    void checkDelete() {
//        ExtrasClaim extrasClaim = createExtrasClaim();
//        extrasClaimRepository.save(extrasClaim);
//
//        extrasClaimRepository.delete(extrasClaim);
//
//        boolean actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).isEmpty();
//        assertThat(actualExtrasClaim).isNotEqualTo(extrasClaim);
//    }
//
//    @Test
//    void checkUpdate() {
//        ExtrasClaim extrasClaim = createExtrasClaim();
//        extrasClaimRepository.save(extrasClaim);
//        Extras extrasUpdated = createExtrasUpdated();
//        extrasClaim.setExtras(extrasUpdated);
//
//        extrasClaimRepository.update(extrasClaim);
//
//        ExtrasClaim actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).get();
//        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
//    }
//
//
//    @Test
//    void checkFindAll() {
//        ExtrasClaim extrasClaim1 = createExtrasClaim();
//        ExtrasClaim extrasClaim2 = createExtrasClaim2();
//        extrasClaimRepository.save(extrasClaim1);
//        extrasClaimRepository.save(extrasClaim2);
//
//        List<ExtrasClaim> extrasClaims = extrasClaimRepository.findAll();
//
//        assertThat(extrasClaims).contains(extrasClaim1, extrasClaim2);
//    }
//
//    @Test
//    void checkFindById() {
//        ExtrasClaim extrasClaim = createExtrasClaim();
//        extrasClaimRepository.save(extrasClaim);
//
//        ExtrasClaim actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).get();
//
//        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
//    }
//
//    private ExtrasClaim createExtrasClaim() {
//        Extras extras = createExtras();
//        Claim claim = createClaim();
//        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
//        return extrasClaim;
//    }
//
//    private ExtrasClaim createExtrasClaim2() {
//        Extras extras = createExtrasUpdated();
//        Claim claim = createClaim();
//        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
//        return extrasClaim;
//    }
//
//    private User createClient() {
//        User client = User.builder()
//                .firstName("TestName")
//                .lastName("TestLastName")
//                .login("TestLogin4")
//                .password("TestPassword")
//                .phoneNumber("TestNumber")
//                .role(Role.USER)
//                .build();
//        session.save(client);
//        return client;
//    }
//
//    private User createAdmin() {
//        User admin = User.builder()
//                .firstName("TestName")
//                .lastName("TestLastName")
//                .login("TestLogin5")
//                .password("TestPassword")
//                .phoneNumber("TestNumber")
//                .role(Role.ADMIN)
//                .build();
//        session.save(admin);
//        return admin;
//    }
//
//    private Sup createSup() {
//        Sup sup = Sup.builder()
//                .model("TestModel")
//                .numberSeats(1)
//                .description("TestDescription")
//                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
//                .build();
//        session.save(sup);
//        return sup;
//    }
//
//    private Claim createClaim() {
//        User client = createClient();
//        User admin = createAdmin();
//        Sup sup = createSup();
//
//        Claim claim = Claim.builder()
//                .client(client)
//                .admin(admin)
//                .sup(sup)
//                .dataStartRent(LocalDate.of(2024, 12, 15))
//                .durationRent(2)
//                .status(Status.OPEN)
//                .price(BigDecimal.valueOf(1200).setScale(2, RoundingMode.HALF_UP))
//                .build();
//        session.save(claim);
//        return claim;
//    }
//
//    private Extras createExtras() {
//        Extras extras = Extras.builder()
//                .name("TestExtra")
//                .description("TestDescription")
//                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
//                .build();
//        session.save(extras);
//        return extras;
//    }
//
//    private Extras createExtrasUpdated() {
//        Extras extrasUpdated = Extras.builder()
//                .name("TestExtrasUpdated")
//                .description("TestDescription")
//                .price(BigDecimal.valueOf(120).setScale(2, RoundingMode.HALF_UP))
//                .build();
//        session.save(extrasUpdated);
//        return extrasUpdated;
//    }
//}
