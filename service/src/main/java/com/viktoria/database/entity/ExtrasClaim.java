package com.viktoria.database.entity;

import jakarta.persistence.Entity;
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

//как правльно для данной сущности переопрделять EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = {"extras", "claim"})
//@ToString(exclude = {"extras", "claim"})
@Entity
@Table(name = "extras_claim")
public class ExtrasClaim implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Extras extras;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;

    public ExtrasClaim(Extras extras, Claim claim) {
        this.extras = extras;
        this.extras.getExtrasClaims().add(this);
        this.claim = claim;
        this.claim.getExtrasClaims().add(this);
    }

}
