package com.senlainc.javacourses.petushokvaliantsin.repositoryapi;

import java.io.Serializable;

public interface IGenericDao<E, K extends Serializable> {

    void create(E object);

    void delete(E object);

    void update(E object);

    E read(K index);

    E read(K index, String graphName);

    Long readCount();
}
