package com.viktoria.dao;

import com.viktoria.entity.Extras;
import jakarta.persistence.EntityManager;

public class ExtrasRepository extends RepositoryBase<Long, Extras> {

    public ExtrasRepository(EntityManager entityManager) {
        super(Extras.class, entityManager);
    }
}
