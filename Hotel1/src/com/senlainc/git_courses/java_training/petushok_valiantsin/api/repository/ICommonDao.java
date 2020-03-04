package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import java.io.Serializable;
import java.util.List;

public interface ICommonDao<T, K extends Serializable> {
    T create(T object);

    void delete(K index);

    void update(T object);

    List<T> readAll();

    T read(K index);
}
