package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<E, K extends Serializable> {

    void create(E object);

    void delete(E object);

    void update(E object);

    Long readSize();

    E read(K index);

    List<E> readAll(int firstElement, int maxResult);

    List<E> readAll(int firstElement, int maxResult, String sortParameter);
}
