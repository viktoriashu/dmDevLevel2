package com.viktoria.entity;

import com.viktoria.TestBase;
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
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim);
        session.flush();
        session.clear();

        Claim actualClaim = session.find(Claim.class, claim.getId());

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
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim);

        claim.setDurationRent(5);
        session.update(claim);
        session.flush();
        session.clear();

        Claim actualClaim = session.find(Claim.class, claim.getId());

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
        session.save(claim);
        session.flush();
        session.clear();

        Claim actualClaim = session.find(Claim.class, claim.getId());

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
                .price(BigDecimal.valueOf(1200))
                .build();
        session.save(claim);
        session.delete(claim);
        session.flush();
        session.clear();

        Claim actualClaim = session.find(Claim.class, claim.getId());

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
        session.save(client);
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
        session.save(admin);
        return admin;
    }

    private Sup createSup() {
        Sup sup = Sup.builder()
                .model("TestModel")
                .numberSeats(1)
                .description("TestDescription")
                .price(BigDecimal.valueOf(100))
                .build();
        session.save(sup);
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
