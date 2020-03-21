package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public final class CustomEntityManager {

    private static final Logger LOGGER = LogManager.getLogger(CustomEntityManager.class);
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private CustomEntityManager() {
        throw new IllegalStateException("Utility class");
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            LOGGER.info("Close Database connection");
        }
    }
}
