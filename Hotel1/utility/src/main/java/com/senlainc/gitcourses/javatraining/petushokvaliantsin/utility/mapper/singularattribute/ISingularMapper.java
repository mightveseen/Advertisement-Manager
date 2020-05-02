package com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.singularattribute;

import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Method;

public interface ISingularMapper {

    void setMethod(Method method);

    <T, E> SingularAttribute<T, E> getSingularAttribute(String parameter);
}
