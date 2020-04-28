package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper;

import java.util.List;

public interface IMapper {

    <D, T> D map(T entity, Class<D> outClazz);

    <D, T> List<D> mapAll(List<T> entities, Class<D> outClazz);
}
