package com.viktoria.spring.database.repository;

import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.dto.sup.SupFilter;
import java.util.List;

public interface FilterSupRepository {

    List<Sup> findBySupFilterQuerydsl(SupFilter filter);

}
