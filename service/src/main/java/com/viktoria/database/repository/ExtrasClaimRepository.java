package com.viktoria.database.repository;

import com.viktoria.database.entity.ExtrasClaim;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ExtrasClaimRepository extends RepositoryBase<Long, ExtrasClaim> {

    public ExtrasClaimRepository(EntityManager entityManager) {
        super(ExtrasClaim.class, entityManager);
    }
}
