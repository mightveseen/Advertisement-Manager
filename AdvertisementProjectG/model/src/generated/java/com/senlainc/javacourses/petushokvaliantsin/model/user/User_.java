package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SetAttribute<User, AdvertisementComment> comments;
	public static volatile SetAttribute<User, Payment> payments;
	public static volatile SingularAttribute<User, Float> rating;
	public static volatile SingularAttribute<User, UserCred> userCred;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SetAttribute<User, Advertisement> advertisements;
	public static volatile SingularAttribute<User, Integer> phone;
	public static volatile SingularAttribute<User, LocalDate> registrationDate;
	public static volatile SetAttribute<User, Chat> chats;
	public static volatile SetAttribute<User, Message> messages;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SetAttribute<User, UserRating> ratedUserRatings;
	public static volatile SetAttribute<User, UserRating> rateOwnerUserRatings;

	public static final String LAST_NAME = "lastName";
	public static final String COMMENTS = "comments";
	public static final String PAYMENTS = "payments";
	public static final String RATING = "rating";
	public static final String USER_CRED = "userCred";
	public static final String FIRST_NAME = "firstName";
	public static final String ADVERTISEMENTS = "advertisements";
	public static final String PHONE = "phone";
	public static final String REGISTRATION_DATE = "registrationDate";
	public static final String CHATS = "chats";
	public static final String MESSAGES = "messages";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String RATED_USER_RATINGS = "ratedUserRatings";
	public static final String RATE_OWNER_USER_RATINGS = "rateOwnerUserRatings";

}

