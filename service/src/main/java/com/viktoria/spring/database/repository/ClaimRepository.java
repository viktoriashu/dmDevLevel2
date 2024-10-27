package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.Claim;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClaimRepository extends RepositoryBase<Long, Claim> {

    public ClaimRepository(EntityManager entityManager) {
        super(Claim.class, entityManager);
    }
}
