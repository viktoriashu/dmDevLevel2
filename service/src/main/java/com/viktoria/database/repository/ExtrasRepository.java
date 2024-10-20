package com.viktoria.database.repository;

import com.viktoria.database.entity.Extras;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ExtrasRepository extends RepositoryBase<Long, Extras> {

    public ExtrasRepository(EntityManager entityManager) {
        super(Extras.class, entityManager);
    }
}
