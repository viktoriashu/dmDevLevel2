package com.viktoria.entity;

import com.viktoria.TestBase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtrasClaimIT extends TestBase {

    @Test
    void checkCreateExtrasClaim() {
        Claim claim = createClaim();
        Extras extras = createExtras();
        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
        session.save(extrasClaim);
        session.flush();
        session.clear();

        ExtrasClaim actualExtrasClaim = session.find(ExtrasClaim.class, extrasClaim.getId());

        assertThat(actualExtrasClaim.getId()).isEqualTo(extrasClaim.getId());
    }

    @Test
    void checkUpdateExtrasClaim() {
        Claim claim = createClaim();
        Extras extras = createExtras();
        Extras extrasUpdated = createExtrasUpdated();
        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
        session.save(extrasClaim);
        extrasClaim.setExtras(extrasUpdated);
        session.update(extrasClaim);
        session.flush();
        session.clear();

        ExtrasClaim actualExtrasClaim = session.find(ExtrasClaim.class, extrasClaim.getId());

        assertThat(actualExtrasClaim.getExtras()).isEqualTo(extrasClaim.getExtras());
    }

    @Test
    void checkReadExtrasClaim() {
        Claim claim = createClaim();
        Extras extras = createExtras();
        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
        session.save(extrasClaim);
        session.flush();
        session.clear();

        ExtrasClaim actualExtrasClaim = session.find(ExtrasClaim.class, extrasClaim.getId());

        assertThat(actualExtrasClaim).isEqualTo(extrasClaim);
    }

    @Test
    void checkDeleteClaim() {
        Claim claim = createClaim();
        Extras extras = createExtras();
        ExtrasClaim extrasClaim = new ExtrasClaim(extras, claim);
        session.save(extrasClaim);
        session.delete(extrasClaim);
        session.flush();
        session.clear();

        ExtrasClaim actualExtrasClaim = session.find(ExtrasClaim.class, extrasClaim.getId());

        assertThat(actualExtrasClaim).isNull();
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
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        session.save(sup);
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
        session.save(claim);
        return claim;
    }

    private Extras createExtras() {
        Extras extras = Extras.builder()
                .name("TestExtra")
                .description("TestDescription")
                .price(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP))
                .build();
        session.save(extras);
        return extras;
    }

    private Extras createExtrasUpdated() {
        Extras extrasUpdated = Extras.builder()
                .name("TestExtrasUpdated")
                .description("TestDescription")
                .price(BigDecimal.valueOf(120).setScale(2, RoundingMode.HALF_UP))
                .build();
        session.save(extrasUpdated);
        return extrasUpdated;
    }
}
