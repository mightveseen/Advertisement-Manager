package com.senlainc.javacourses.petushokvaliantsin.repositoryapi;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;

public interface IGenericDao<E, K extends Serializable> {

    void create(E object);

    void delete(E object);

    void update(E object);

    E read(K index);

    <F> E read(SingularAttribute<E, F> field);

    <F> F read(K index, SingularAttribute<E, F> field);

    List<E> readAll(int firstElement, int maxResult);

    <F> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> sortField);

    <F> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> sortField, F parameter);

    K readSize();
}
