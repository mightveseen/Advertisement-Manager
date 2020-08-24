package com.senlainc.javacourses.petushokvaliantsin.repositoryapi;

import java.io.Serializable;
import java.util.Optional;

public interface IGenericDao<E, K extends Serializable> {

    void create(E object);

    void delete(E object);

    void update(E object);

    Optional<E> read(K index);

    Optional<E> read(K index, String graphName);

    Long readCount();
}
