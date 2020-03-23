package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import java.io.Serializable;
import java.util.List;

public interface ICommonDao<T, K extends Serializable> {

    void create(T object);

    void delete(K index);

    void update(T object);

    List<T> readAll();

    Long readSize();

    List<T> readAllPagination(int firstElement, int maxResult);

    List<T> readAllPagination(int firstElement, int maxResult, String parameter);

    T read(K index);
}
