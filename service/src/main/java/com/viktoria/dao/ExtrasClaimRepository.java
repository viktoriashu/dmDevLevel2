package com.viktoria.dao;

import com.viktoria.entity.ExtrasClaim;
import jakarta.persistence.EntityManager;

public class ExtrasClaimRepository extends RepositoryBase<Long, ExtrasClaim> {

    public ExtrasClaimRepository(EntityManager entityManager) {
        super(ExtrasClaim.class, entityManager);
    }
}
