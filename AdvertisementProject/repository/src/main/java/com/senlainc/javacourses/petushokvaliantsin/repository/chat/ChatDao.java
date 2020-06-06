package com.senlainc.javacourses.petushokvaliantsin.repository.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SetAttribute;
import java.util.List;

@Repository
public class ChatDao extends AbstractDao<Chat, Long> implements IChatDao {

    @Override
    public <F> List<Chat> readAll(IPageParameter pageParameter, SetAttribute<Chat, F> field, F value) {
        try {
            final CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Chat> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.get(field), value);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(pageParameter.getOrder(criteriaBuilder, root))
                    .where(predicate))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
