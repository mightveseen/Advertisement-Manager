package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection;


import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@DependencyClass
public final class HibernateSessionFactory {
    private SessionFactory sessionFactory;

    private HibernateSessionFactory() {
        final Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Attendance.class);
        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
