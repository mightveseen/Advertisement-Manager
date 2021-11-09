package com.senlainc.javacourses.petushokvaliantsin.utility.mapper;

import javax.persistence.metamodel.SingularAttribute;

public interface ISingularMapper {

    <T, E> SingularAttribute<T, E> getAttribute(String parameter);
}
