package com.senlainc.javacourses.petushokvaliantsin.utility.mapper;

import jakarta.persistence.metamodel.SingularAttribute;

public interface ISingularMapper {

    <T, E> SingularAttribute<T, E> getAttribute(String parameter);
}
