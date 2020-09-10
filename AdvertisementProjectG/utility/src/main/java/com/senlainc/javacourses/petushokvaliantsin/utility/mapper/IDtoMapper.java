package com.senlainc.javacourses.petushokvaliantsin.utility.mapper;

import java.util.List;

public interface IDtoMapper {

    <S, D> D map(S object, Class<D> clazz);

    <S, D> List<D> mapAll(List<S> objects, Class<D> clazz);
}
