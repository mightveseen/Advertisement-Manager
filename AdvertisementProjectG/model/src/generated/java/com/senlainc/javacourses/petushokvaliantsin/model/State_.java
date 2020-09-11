package com.senlainc.javacourses.petushokvaliantsin.model;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(State.class)
public abstract class State_ {

    public static volatile SetAttribute<State, Advertisement> advertisements;
    public static volatile SetAttribute<State, Payment> payments;
    public static volatile SingularAttribute<State, String> description;
    public static volatile SingularAttribute<State, Long> id;

    public static final String ADVERTISEMENTS = "advertisements";
    public static final String PAYMENTS = "payments";
    public static final String DESCRIPTION = "description";
    public static final String ID = "id";

}

