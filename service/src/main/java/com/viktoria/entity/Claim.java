package com.viktoria.entity;

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

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"client", "admin", "sup", "extras"})
@Entity
@Table(name = "claim")
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

    @JoinColumn(name = "extras_id", table = "claim")
    @ManyToOne(fetch = FetchType.LAZY)
    private Extras extras;

    @Column(name = "data_start_rent", table = "claim")
    private LocalDate dataStartRent;

    @Column(name = "duration_rent", table = "claim")
    private int durationRent;

    @Column(name = "status", table = "claim")
    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal price;

}
