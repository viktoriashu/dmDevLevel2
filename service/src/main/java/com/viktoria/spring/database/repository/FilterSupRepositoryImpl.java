package com.viktoria.spring.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.dto.sup.SupFilter;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import org.springframework.data.jpa.repository.EntityGraph;

import static com.viktoria.spring.database.entity.QSup.sup;

@RequiredArgsConstructor
public class FilterSupRepositoryImpl implements FilterSupRepository {

    private final EntityManager entityManager;

    @EntityGraph(attributePaths = {"claim"})
    @Override
    public List<Sup> findBySupFilterQuerydsl(SupFilter filter) {
        Map<String, Object> properties = Map.of(GraphSemantic.FETCH.getJpaHintName(),
                entityManager.getEntityGraph("Sup.claim"));

        var predicate = QPredicate.builder()
                .add(filter.getModel(), sup.model::eq)
                .add(filter.getNumberSeats(), sup.numberSeats::eq)
                .buildAnd();

        return new JPAQuery<Sup>(entityManager)
                .select(sup)
                .from(sup)
                .where(predicate)
                .orderBy(sup.price.asc())
                .setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph("Sup.claim"))
                .fetch();
    }
}
