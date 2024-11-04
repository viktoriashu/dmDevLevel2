package com.viktoria.spring.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.database.entity.Sup_;
import com.viktoria.spring.dto.SupFilter;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
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
//        Map<String, Object> properties = Map.of(GraphSemantic.FETCH.getJpaHintName(),
//                entityManager.getEntityGraph("Sup.claim"));

        var predicate = QPredicate.builder()
                .add(filter.getModel(), sup.model::eq)
                .add(filter.getNumberSeats(), sup.numberSeats::eq)
                .buildAnd();

        return new JPAQuery<Sup>(entityManager)
                .select(sup)
                .from(sup)
                .where(predicate)
                .orderBy(sup.price.asc())
//                .setHint(GraphSemantic.FETCH.getJpaHintName(), entityManager.getEntityGraph("Sup.claim"))
                .fetch();
    }

    public List<Sup> findBySupFilterCriteriaApi(SupFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Sup.class);
        var sup = criteria.from(Sup.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getModel() != null) {
            predicates.add(cb.equal(sup.get(Sup_.model), filter.getModel()));
        }
        if (filter.getNumberSeats() != 0) {
            predicates.add(cb.equal(sup.get(Sup_.numberSeats), filter.getNumberSeats()));
        }

        criteria.select(sup).where(predicates.toArray(Predicate[]::new)).orderBy(cb.asc(sup.get(Sup_.PRICE)));

        return entityManager.createQuery(criteria)
                .getResultList();
    }

}
