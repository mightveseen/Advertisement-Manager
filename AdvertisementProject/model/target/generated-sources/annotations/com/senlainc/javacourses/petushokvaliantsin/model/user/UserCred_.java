package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserCred.class)
public abstract class UserCred_ {

	public static volatile SingularAttribute<UserCred, String> password;
	public static volatile SingularAttribute<UserCred, EnumRole> enumRole;
	public static volatile SingularAttribute<UserCred, Long> id;
	public static volatile SingularAttribute<UserCred, User> user;
	public static volatile SingularAttribute<UserCred, String> username;

	public static final String PASSWORD = "password";
	public static final String ENUM_ROLE = "enumRole";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String USERNAME = "username";

}

