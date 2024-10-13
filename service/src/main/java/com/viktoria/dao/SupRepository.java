package com.viktoria.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.viktoria.dto.SupFilter;
import com.viktoria.entity.Sup;
import com.viktoria.entity.Sup_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.graph.GraphSemantic;

import static com.viktoria.entity.QSup.sup;

public class SupRepository extends RepositoryBase<Long, Sup> {

    public SupRepository(EntityManager entityManager) {
        super(Sup.class, entityManager);
    }

    /**
     * Возвращает сапы указанной модели и с указанным кол-вом мест отсортированные в порядке возрастания цены
     * CriteriaAPI
     */
    public List<Sup> findBySupFilterCriteriaApi(SupFilter filter) {
        var cb = getEntityManager().getCriteriaBuilder();
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

        return getEntityManager().createQuery(criteria)
                .getResultList();
    }

    /**
     * Возвращает сапы указанной модели и с указанным кол-вом мест отсортированные в порядке возрастания цены
     * Querydsl
     */
    public List<Sup> findBySupFilterQuerydsl(SupFilter filter) {
        Map<String, Object> properties = Map.of(GraphSemantic.FETCH.getJpaHintName(),
                getEntityManager().getEntityGraph("WithClaim"));

        var predicate = QPredicate.builder()
                .add(filter.getModel(), sup.model::eq)
                .add(filter.getNumberSeats(), sup.numberSeats::eq)
                .buildAnd();

        return new JPAQuery<Sup>(getEntityManager())
                .select(sup)
                .from(sup)
                .where(predicate)
                .orderBy(sup.price.asc())
                .setHint(GraphSemantic.FETCH.getJpaHintName(), getEntityManager().getEntityGraph("WithClaim"))
                .fetch();
    }

}
