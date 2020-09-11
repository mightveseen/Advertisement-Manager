package com.senlainc.javacourses.petushokvaliantsin.dao;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.ChatDaoCustomQuery;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ChatDaoImpl extends AbstractDao<Chat> implements ChatDaoCustomQuery {

    @Override
    public List<Chat> readAllUserChat(IPageParameter pageParameter, User user) {
        try {
            final CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Chat> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.join(Chat_.users), user);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(getOrder(pageParameter, criteriaBuilder, root))
                    .where(predicate))
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(GraphProperty.CHAT_DEFAULT))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Long readCountWithUser(User user) {
        try {
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<Chat> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.join(Chat_.users), user);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
