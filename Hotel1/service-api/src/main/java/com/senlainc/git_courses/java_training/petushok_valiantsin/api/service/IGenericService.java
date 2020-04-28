package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<E, K extends Serializable> {

    void create(E object);

    void delete(K index);

    void update(E object);

    E read(K index);

    List<E> readAll(int firstElement, int maxResult);
}
