package com.senlainc.javacourses.petushokvaliantsin.model.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRating.class)
public abstract class UserRating_ {

	public static volatile SingularAttribute<UserRating, Long> id;
	public static volatile SingularAttribute<UserRating, User> rateOwnerUser;
	public static volatile SingularAttribute<UserRating, Short> value;
	public static volatile SingularAttribute<UserRating, User> ratedUser;

	public static final String ID = "id";
	public static final String RATE_OWNER_USER = "rateOwnerUser";
	public static final String VALUE = "value";
	public static final String RATED_USER = "ratedUser";

}

