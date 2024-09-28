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
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Claim {

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

    private LocalDate dataStartRent;
    private int durationRent;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal price;

}
