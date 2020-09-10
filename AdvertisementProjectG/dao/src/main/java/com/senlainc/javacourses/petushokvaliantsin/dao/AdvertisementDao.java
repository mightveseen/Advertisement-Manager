package com.senlainc.javacourses.petushokvaliantsin.dao;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory_;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvertisementDao extends AbstractDao<Advertisement, Long> implements IAdvertisementDao {

    private static final String NONE_PARAMETER = "none";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdvertisementDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Advertisement> readAllWithUser(IPageParameter pageParameter, User user, State state) {
        try {
            final TypedQuery<Advertisement> query = entityManager.createQuery(
                    "select a from Advertisement a\n" +
                            "left join a.user u \n" +
                            "left join a.state s \n" +
                            "where u = :user and s = :state", Advertisement.class)
                    .setParameter("user", user)
                    .setParameter("state", state);
            return query.setFirstResult(pageParameter.getFirstElement()).setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    //FIXME : Fix order by
    @Override
    public List<Advertisement> readAllWithFilter(IPageParameter pageParameter, IFilterParameter filterParameter, IStateParameter stateParameter) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final List<Predicate> predicates = getPredicates(root, filterParameter, stateParameter);
            final List<Order> orders = getOrders(pageParameter, root, stateParameter);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(orders)
//                    .groupBy(root, root.get(Advertisement_.id), root.join(Advertisement_.user).get(User_.id))
                    .where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(GraphProperty.Advertisement.DEFAULT))
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Long readCountWitFilter(IFilterParameter filterParameter, IStateParameter stateParameter) {
        try {
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final List<Predicate> predicates = getPredicates(root, filterParameter, stateParameter);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root))
                    .where(predicates.toArray(new Predicate[0])))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public List<Advertisement> tryJdbcTemplate() {
        final String query = "select * from advertisements a \n" +
                "left join users u on a.owner_id = u.id \n" +
                "left join user_creds uc on uc.id = u.id \n";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            final Advertisement advertisement = new Advertisement();
            advertisement.setId(resultSet.getLong("id"));
            advertisement.setHeader(resultSet.getString("header"));
            advertisement.setDescription(resultSet.getString("description"));
            advertisement.setDate(resultSet.getDate("date").toLocalDate());
            advertisement.setPrice(resultSet.getDouble("price"));
            final UserCred userCred = new UserCred(resultSet.getString("username"), resultSet.getString("password"),
                    EnumRole.valueOf(resultSet.getString("role")));
            userCred.setId(resultSet.getLong("owner_id"));
            final User user = new User(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),
                    resultSet.getInt("phone"), resultSet.getDate("registration_date").toLocalDate(), resultSet.getFloat("rating"));
            user.setUserCred(userCred);
            user.setId(resultSet.getLong("owner_id"));
            advertisement.setUser(user);
            return advertisement;
        });
    }

    /**
     * Problem with duplicated fields (while make left join and after getting
     * in result 3 fields with name 'id' - SQL Syntax Exception
     *
     * @return List with type Advertisement
     */
    @Override
    @SuppressWarnings("uncheked")
    public List<Advertisement> tryNativeQuery() {
        final String query = "select * from advertisements a \n" +
                "where a.price > 100";
        return entityManager.createNativeQuery(query).getResultList();
    }

    private List<Predicate> getPredicates(Root<Advertisement> root, IFilterParameter filterParameter, IStateParameter stateParameter) {
        final List<Predicate> predicates = new ArrayList<>(6);
        if (!filterParameter.getSearch().equals(NONE_PARAMETER)) {
            predicates.add(criteriaBuilder.like(root.get(Advertisement_.header), "%" + filterParameter.getSearch() + "%"));
        }
        if (!filterParameter.getCategory().equals(NONE_PARAMETER)) {
            final Join<Advertisement, AdvertisementCategory> join = root.join(Advertisement_.category);
            predicates.add(criteriaBuilder.equal(join.get(AdvertisementCategory_.description), filterParameter.getCategory()));
        }
        if (filterParameter.getMinPrice() > 0) {
            predicates.add(criteriaBuilder.ge(root.get(Advertisement_.price), filterParameter.getMinPrice()));
        }
        if (filterParameter.getMaxPrice() > 0) {
            predicates.add(criteriaBuilder.le(root.get(Advertisement_.price), filterParameter.getMaxPrice()));
        }
        if (stateParameter.getAdvertisementState() != null && !stateParameter.getAdvertisementState().equals(EnumState.ALL)) {
            predicates.add(criteriaBuilder.equal(root.get(Advertisement_.state), stateParameter.getAdvertisementState()));
        }
        return predicates;
    }

    private List<Order> getOrders(IPageParameter pageParameter, Root<Advertisement> root, IStateParameter stateParameter) {
        final List<Order> orders = new ArrayList<>();
        if (pageParameter.getCriteriaField().length > 1 && pageParameter.getCriteriaField()[1].getName().equals("rating")) {
            final Join<Advertisement, Payment> join = root.join(Advertisement_.payments, JoinType.LEFT);
            join.on(criteriaBuilder.equal(join.get(Payment_.state), stateParameter.getPaymentState()));
            orders.add(criteriaBuilder.asc(join.join(Advertisement_.USER, JoinType.LEFT).get(User_.RATING)));
        }
        orders.add(getOrder(pageParameter, criteriaBuilder, root));
        return orders;
    }
}
