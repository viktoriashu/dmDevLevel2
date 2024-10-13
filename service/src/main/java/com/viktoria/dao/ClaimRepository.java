package com.viktoria.dao;

import com.viktoria.entity.Claim;
import jakarta.persistence.EntityManager;

public class ClaimRepository extends RepositoryBase<Long, Claim> {

    public ClaimRepository(EntityManager entityManager) {
        super(Claim.class, entityManager);
    }
}
