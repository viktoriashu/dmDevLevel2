package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
