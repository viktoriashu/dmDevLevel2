package com.viktoria.spring.database.repository;

import com.viktoria.spring.IntegrationTestBase;
import com.viktoria.spring.database.entity.Claim;
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
public class ClaimRepositoryIT extends IntegrationTestBase {

    private final ClaimRepository claimRepository;
    private final EntityManager entityManager;

    @Test
    void checkSave() {
        Claim claim = createClaim();

        claimRepository.save(claim);

        Claim actualClaim = claimRepository.findById(claim.getId()).get();
        assertThat(actualClaim).isEqualTo(claim);
    }

    @Test
    void checkDelete() {
        Claim claim = createClaim();
        claimRepository.save(claim);

        claimRepository.delete(claim);

        boolean actualClaim = claimRepository.findById(claim.getId()).isEmpty();
        assertThat(actualClaim).isNotEqualTo(claim);
    }

    @Test
    void checkUpdate() {
        Claim claim = createClaim();
        claimRepository.save(claim);
        claim.setStatus(Status.CLOSE);

        claimRepository.update(claim);

        Claim actualClaim = claimRepository.findById(claim.getId()).get();
        assertThat(actualClaim.getStatus()).isEqualTo(Status.CLOSE);
    }

    @Test
    void checkFindAll() {
        Claim claim1 = createClaim();
        Claim claim2 = createClaim2();
        claimRepository.save(claim1);
        claimRepository.save(claim2);

        List<Claim> claims = claimRepository.findAll();

        assertThat(claims).contains(claim1, claim2);
    }

    @Test
    void checkFindById() {
        Claim claim = createClaim();
        claimRepository.save(claim);

        Claim actualClaim = claimRepository.findById(claim.getId()).get();

        assertThat(actualClaim).isEqualTo(claim);
    }

    private User createClient() {
        User client = User.builder()
                .firstName("TestName")
                .lastName("TestLastName")
                .login("TestLogin4")
                .password("TestPassword")
                .phoneNumber("TestNumber")
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
                .phoneNumber("TestNumber")
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
        return claim;
    }

    private Claim createClaim2() {
        User client = createClient();
        User admin = createAdmin();
        Sup sup = createSup();

        Claim claim = Claim.builder()
                .client(client)
                .admin(admin)
                .sup(sup)
                .dataStartRent(LocalDate.of(2024, 12, 20))
                .durationRent(2)
                .status(Status.OPEN)
                .price(BigDecimal.valueOf(1200).setScale(2, RoundingMode.HALF_UP))
                .build();
        return claim;
    }

}
