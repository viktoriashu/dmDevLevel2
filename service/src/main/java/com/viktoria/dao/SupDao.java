package com.viktoria.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.viktoria.dto.SupFilter;
import com.viktoria.entity.Sup;
import com.viktoria.entity.Sup_;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import static com.viktoria.entity.QSup.sup;


@NoArgsConstructor
public class SupDao {

    public static final SupDao INSTANCE = new SupDao();

    public static SupDao getInstance() {
        return INSTANCE;
    }

    /**
     * Возвращает все sup отсортированные в порядке возрастания цены
     * CriteriaAPI
     */
    public List<Sup> findAllSupByPriceCriteriaAPI(Session session) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Sup.class);
        var sup = criteria.from(Sup.class);

        criteria.select(sup).orderBy(cb.asc(sup.get(Sup_.PRICE)));

        return session.createQuery(criteria)
                .list();
    }

    /**
     * Возвращает все sup отсортированные в порядке возрастания цены
     * Querydsl
     */
    public List<Sup> findAllSupByPriceQuerydsl(Session session) {
        return new JPAQuery<Sup>(session)
                .select(sup)
                .from(sup)
                .orderBy(sup.price.asc())
                .fetch();
    }

    /**
     * Возвращает сапы указанной модели и с указанным кол-вом мест отсортированные в порядке возрастания цены
     * CriteriaAPI
     */
    public List<Sup> findBySupFilterCriteriaApi(Session session, SupFilter filter) {
        var cb = session.getCriteriaBuilder();
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

        return session.createQuery(criteria)
                .list();
    }

    /**
     * Возвращает сапы указанной модели и с указанным кол-вом мест отсортированные в порядке возрастания цены
     * Querydsl
     */
    public List<Sup> findBySupFilterQuerydsl(Session session, SupFilter filter) {
        Map<String, Object> properties = Map.of(GraphSemantic.FETCH.getJpaHintName(),
                session.getEntityGraph("WithClaim"));

        var predicate = QPredicate.builder()
                .add(filter.getModel(), sup.model::eq)
                .add(filter.getNumberSeats(), sup.numberSeats::eq)
                .buildAnd();

        return new JPAQuery<Sup>(session)
                .select(sup)
                .from(sup)
                .where(predicate)
                .orderBy(sup.price.asc())
                .setHint(GraphSemantic.FETCH.getJpaHintName(), session.getEntityGraph("WithClaim"))
                .fetch();
    }

}
