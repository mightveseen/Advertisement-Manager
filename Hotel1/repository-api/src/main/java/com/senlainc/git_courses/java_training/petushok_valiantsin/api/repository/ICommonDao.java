package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import java.io.Serializable;
import java.util.List;

public interface ICommonDao<T, K extends Serializable> {

    void create(T object);

    void delete(K index);

    void update(T object);

    Long readSize();

    List<T> readAll(int firstElement, int maxResult);

    List<T> readAll(int firstElement, int maxResult, String sortParameter);

    T read(K index);
}
