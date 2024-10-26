package com.viktoria.entity;

import com.viktoria.TestBase;
import com.viktoria.database.entity.Claim;
import com.viktoria.database.entity.Role;
import com.viktoria.database.entity.Status;
import com.viktoria.database.entity.Sup;
import com.viktoria.database.entity.User;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimIT extends TestBase {

    @Test
    void checkCreateClaim() {
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

        entityManager.flush();
        entityManager.clear();
        Claim actualClaim = entityManager.find(Claim.class, claim.getId());
        assertThat(actualClaim.getId()).isEqualTo(claim.getId());
    }

    @Test
    void checkUpdateClaim() {
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
        claim.setDurationRent(5);

        entityManager.merge(claim);

        entityManager.flush();
        entityManager.clear();
        Claim actualClaim = entityManager.find(Claim.class, claim.getId());
        assertThat(actualClaim.getDurationRent()).isEqualTo(claim.getDurationRent());
    }

    @Test
    void checkReadClaim() {
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
        entityManager.flush();
        entityManager.clear();

        Claim actualClaim = entityManager.find(Claim.class, claim.getId());

        assertThat(actualClaim).isEqualTo(claim);
    }

    @Test
    void checkDeleteClaim() {
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

        entityManager.remove(claim);

        entityManager.flush();
        entityManager.clear();
        Claim actualClaim = entityManager.find(Claim.class, claim.getId());
        assertThat(actualClaim).isNull();
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

//    private Extras createExtras() {
//        Extras extras = Extras.builder()
//                .name("TestExtra")
//                .description("TestDescription")
//                .price(BigDecimal.valueOf(100))
//                .build();
//        session.save(extras);
//        return extras;
//    }
}
