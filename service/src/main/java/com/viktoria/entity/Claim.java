package com.viktoria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_client", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @JoinColumn(name = "id_admin", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private User admin;

    @JoinColumn(name = "id_sup", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sup sup;

    private LocalDate dataStartRent;

    @Enumerated(EnumType.STRING)
    private StatusSup statusSup;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int durationRent;
    private BigDecimal price;

}
