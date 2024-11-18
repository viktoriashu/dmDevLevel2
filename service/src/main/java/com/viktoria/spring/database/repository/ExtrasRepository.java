package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.Extras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ExtrasRepository extends JpaRepository<Extras, Long>, QuerydslPredicateExecutor<Extras> {

}
