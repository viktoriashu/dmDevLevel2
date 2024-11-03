package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.ExtrasClaim;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ExtrasClaimRepository extends RepositoryBase<Long, ExtrasClaim> {

    public ExtrasClaimRepository(EntityManager entityManager) {
        super(ExtrasClaim.class, entityManager);
    }
}
