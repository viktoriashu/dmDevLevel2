package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ClaimRepository extends JpaRepository<Claim, Long>, QuerydslPredicateExecutor<Claim> {

}
