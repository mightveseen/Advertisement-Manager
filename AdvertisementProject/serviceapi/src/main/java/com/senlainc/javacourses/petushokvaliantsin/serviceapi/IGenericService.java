package com.senlainc.javacourses.petushokvaliantsin.serviceapi;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<E, K extends Serializable> {

    boolean create(E object);

    boolean delete(K index);

    boolean update(E object);

    E read(K index);

    List<E> readAll(int firstElement, int maxResult, String direction);
}
