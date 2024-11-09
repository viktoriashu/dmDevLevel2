package com.viktoria.spring.database.repository;

import com.viktoria.spring.IntegrationTestBase;
import com.viktoria.spring.database.entity.Claim;
import com.viktoria.spring.database.entity.Extras;
import com.viktoria.spring.database.entity.ExtrasClaim;
import com.viktoria.spring.database.entity.Role;
import com.viktoria.spring.database.entity.Status;
import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.database.entity.User;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ExtrasClaimRepositoryIT extends IntegrationTestBase {

    private final ExtrasClaimRepository extrasClaimRepository;
    private final EntityManager entityManager;

    @Test
    void checkSave() {
        ExtrasClaim extrasClaim = createExtrasClaim();

        extrasClaimRepository.save(extrasClaim);

        ExtrasClaim actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).get();
        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
    }

    @Test
    void checkDelete() {
        ExtrasClaim extrasClaim = createExtrasClaim();
        extrasClaimRepository.save(extrasClaim);

        extrasClaimRepository.delete(extrasClaim);

        boolean actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).isEmpty();
        assertThat(actualExtrasClaim).isNotEqualTo(extrasClaim);
    }

    @Test
    void checkUpdate() {
        ExtrasClaim extrasClaim = createExtrasClaim();
        extrasClaimRepository.save(extrasClaim);
        entityManager.clear();
        Extras extrasUpdated = createExtrasUpdated();

        extrasClaim.setExtras(extrasUpdated);
        extrasClaimRepository.saveAndFlush(extrasClaim);
        entityManager.clear();

        ExtrasClaim actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).get();
        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
    }


    @Test
    void checkFindAll() {
        ExtrasClaim extrasClaim1 = createExtrasClaim();
        ExtrasClaim extrasClaim2 = ExtrasClaim.builder()
                .extras(extrasClaim1.getExtras())
                .claim(extrasClaim1.getClaim())
                .build();
        extrasClaimRepository.save(extrasClaim1);
        extrasClaimRepository.save(extrasClaim2);

        List<ExtrasClaim> extrasClaims = extrasClaimRepository.findAll();

        assertThat(extrasClaims).contains(extrasClaim1, extrasClaim2);
    }

    @Test
    void checkFindById() {
        ExtrasClaim extrasClaim = createExtrasClaim();
        extrasClaimRepository.save(extrasClaim);

        ExtrasClaim actualExtrasClaim = extrasClaimRepository.findById(extrasClaim.getId()).get();

        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
    }

    private ExtrasClaim createExtrasClaim() {
        Extras extras = createExtras();
        Claim claim = createClaim();
        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
        return extrasClaim;
    }

    private User createClient() {
        User client = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin4")
                .password("TestPassword")
                .phoneNumber("TestNumberClient")
                .role(Role.USER)
                .build();
        entityManager.persist(client);
        return client;
    }

    private User createAdmin() {
        User admin = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin5")
                .password("TestPassword")
                .phoneNumber("TestNumberAdmin")
                .role(Role.ADMIN)
                .build();
        entityManager.persist(admin);
        return admin;
    }

    private Sup createSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(sup);
        return sup;
    }

    private Claim createClaim() {
        User client = createClient();
        User admin = createAdmin();
        Sup sup = createSup();

        Claim claim = Claim.builder()
                .client(client)
                .admin(admin)
                .sup(sup)
                .dataStartRent(LocalDate.of(2024, 12, 15))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(claim);
        return claim;
    }

    private Extras createExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(extras);
        return extras;
    }

    private Extras createExtrasUpdated() {
        Extras extrasUpdated = Extras.builder()
                .name("TestExtrasUpdated")
                .description("TestDescription")
                .price(BigDecimal.valueOf(120).setScale(2, RoundingMode.HALF_UP))
                .build();
        entityManager.persist(extrasUpdated);
        return extrasUpdated;
    }
}
