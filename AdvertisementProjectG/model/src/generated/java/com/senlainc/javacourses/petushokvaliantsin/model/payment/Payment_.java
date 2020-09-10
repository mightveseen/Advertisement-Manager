package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Payment.class)
public abstract class Payment_ {

	public static final String END_DATE = "endDate";
	public static final String PRICE = "price";
	public static final String ADVERTISEMENT = "advertisement";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String TYPE = "type";
	public static final String USER = "user";
	public static final String START_DATE = "startDate";
	public static volatile SingularAttribute<Payment, LocalDate> endDate;
	public static volatile SingularAttribute<Payment, Double> price;
	public static volatile SingularAttribute<Payment, Advertisement> advertisement;
	public static volatile SingularAttribute<Payment, Long> id;
	public static volatile SingularAttribute<Payment, State> state;
	public static volatile SingularAttribute<Payment, PaymentType> type;
	public static volatile SingularAttribute<Payment, User> user;
	public static volatile SingularAttribute<Payment, LocalDate> startDate;

}

