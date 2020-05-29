package com.senlainc.javacourses.petushokvaliantsin.serviceapi;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;

public interface IGenericService<E, K extends Serializable> {

    boolean create(E object);

    boolean remove(K index);

    boolean update(E object);

    E read(K index);

    List<E> readAll(int firstElement, int maxResult);

    <C> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, C> sortField);
}
