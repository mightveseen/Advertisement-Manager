package com.senlainc.javacourses.petushokvaliantsin.configuration;

import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.implementation.SingularMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.senlainc.javacourses.petushokvaliantsin.repository",
        "com.senlainc.javacourses.petushokvaliantsin.service",
        "com.senlainc.javacourses.petushokvaliantsin.controller",
        "com.senlainc.javacourses.petushokvaliantsin.utility"})
@PropertySource(value = {"classpath:/properties/base.properties"})
@EnableTransactionManagement
public class AppConfig {

    @Value(value = "${DATABASE.URL}")
    private String url;
    @Value(value = "${DATABASE.USER}")
    private String user;
    @Value(value = "${DATABASE.PASSWORD}")
    private String password;
    @Value(value = "${DATABASE.MODEL_PACKAGE}")
    private String[] modelPackagePath;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");
        hibernateProperties.setProperty("hibernate.connection.charSet", "UTF-8");
        hibernateProperties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return hibernateProperties;
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        final DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        managerDataSource.setUrl(url);
        managerDataSource.setUsername(user);
        managerDataSource.setPassword(password);
        return managerDataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(driverManagerDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        entityManagerFactoryBean.setPersistenceUnitName("mainPersistence");
        entityManagerFactoryBean.setPackagesToScan(modelPackagePath);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public ISingularMapper singularMapper() {
        return new SingularMapper("com.senlainc.javacourses.petushokvaliantsin.service");
    }
}
