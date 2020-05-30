package com.senlainc.javacourses.petushokvaliantsin.utility.mapper;

import javax.persistence.metamodel.SingularAttribute;

public interface ISingularMapper {

    void setClass(Class<?> clazz);

    <T, E> SingularAttribute<T, E> getSingularAttribute(String parameter);
}
