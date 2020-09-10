package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AdvertisementComment.class)
public abstract class AdvertisementComment_ {

	public static final String DATE_TIME = "dateTime";
	public static final String ADVERTISEMENT = "advertisement";
	public static final String ID = "id";
	public static final String MESSAGE = "message";
	public static final String USER = "user";
	public static volatile SingularAttribute<AdvertisementComment, LocalDateTime> dateTime;
	public static volatile SingularAttribute<AdvertisementComment, Advertisement> advertisement;
	public static volatile SingularAttribute<AdvertisementComment, Long> id;
	public static volatile SingularAttribute<AdvertisementComment, String> message;
	public static volatile SingularAttribute<AdvertisementComment, User> user;

}

