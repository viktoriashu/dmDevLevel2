package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository

public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
