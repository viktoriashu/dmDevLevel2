package com.viktoria.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"client", "admin", "sup", "extrasClaims"})
@ToString(exclude = {"client", "admin", "sup", "extrasClaims"})
@Entity
@Table(name = "claim")
public class Claim implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "client_id", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @JoinColumn(name = "admin_id", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private User admin;

    @JoinColumn(name = "sup_id", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sup sup;

    @Builder.Default
    @OneToMany(mappedBy = "claim")
    private List<ExtrasClaim> extrasClaims = new ArrayList<>();

    @Column(name = "data_start_rent", table = "claim")
    private LocalDate dataStartRent;

    @Column(name = "duration_rent", table = "claim")
    private int durationRent;

    @Column(name = "status", table = "claim")
    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal price;

}
