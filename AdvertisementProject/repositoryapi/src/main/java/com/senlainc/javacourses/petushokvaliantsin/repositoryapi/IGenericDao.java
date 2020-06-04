package com.senlainc.javacourses.petushokvaliantsin.repositoryapi;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;

public interface IGenericDao<E, K extends Serializable> {

    void create(E object);

    void delete(E object);

    void update(E object);

    E read(K index);

    <F> F read(String indexName, K index, SingularAttribute<E, F> field);

    List<E> readAll(IPageParameter pageParameter);

    <F> List<E> readAll(IPageParameter pageParameter, SingularAttribute<E, F> field, F value);

    Long readSize();
}
