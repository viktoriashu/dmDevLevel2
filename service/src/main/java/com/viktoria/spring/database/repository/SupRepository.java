package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.Sup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupRepository extends JpaRepository<Sup, Long>, FilterSupRepository {

}
