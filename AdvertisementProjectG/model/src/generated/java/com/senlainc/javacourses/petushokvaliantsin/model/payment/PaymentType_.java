package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaymentType.class)
public abstract class PaymentType_ {

	public static volatile SingularAttribute<PaymentType, Integer> duration;
	public static volatile SingularAttribute<PaymentType, Double> price;
	public static volatile SetAttribute<PaymentType, Payment> payments;
	public static volatile SingularAttribute<PaymentType, String> description;
	public static volatile SingularAttribute<PaymentType, Long> id;

	public static final String DURATION = "duration";
	public static final String PRICE = "price";
	public static final String PAYMENTS = "payments";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

