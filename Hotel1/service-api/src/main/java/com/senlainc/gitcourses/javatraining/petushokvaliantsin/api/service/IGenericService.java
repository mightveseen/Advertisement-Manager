package com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<E, K extends Serializable> {

    void create(E object);

    void delete(K index);

    void update(E object);

    E read(K index);

    List<E> readAll(int firstElement, int maxResult);
}
