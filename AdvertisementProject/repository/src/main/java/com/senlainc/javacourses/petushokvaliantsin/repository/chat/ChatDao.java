package com.senlainc.javacourses.petushokvaliantsin.repository.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ChatDao extends AbstractDao<Chat, Long> implements IChatDao {

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
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    //TODO : Fix set (cannot find single user)
    @Override
    public Long readCountWithUser(User user) {
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<Chat> root = criteriaQuery.from(Chat.class);
        final Predicate predicate = criteriaBuilder.equal(root.get(Chat_.users), user);
        return entityManager.createQuery(criteriaQuery
                .select(criteriaBuilder.count(root))
                .where(predicate))
                .getSingleResult();
    }
}
