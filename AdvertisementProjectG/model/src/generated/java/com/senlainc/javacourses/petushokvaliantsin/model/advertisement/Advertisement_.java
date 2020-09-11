package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Advertisement.class)
public abstract class Advertisement_ {

	public static volatile SingularAttribute<Advertisement, LocalDate> date;
	public static volatile SetAttribute<Advertisement, AdvertisementComment> comments;
    public static volatile SingularAttribute<Advertisement, Double> price;
    public static volatile SetAttribute<Advertisement, Payment> payments;
    public static volatile SingularAttribute<Advertisement, String> header;
    public static volatile SingularAttribute<Advertisement, String> description;
    public static volatile SingularAttribute<Advertisement, Long> id;
    public static volatile SingularAttribute<Advertisement, State> state;
    public static volatile SingularAttribute<Advertisement, AdvertisementCategory> category;
    public static volatile SingularAttribute<Advertisement, User> user;
    public static volatile SetAttribute<Advertisement, AdvertisementPhoto> photos;

    public static final String DATE = "date";
    public static final String COMMENTS = "comments";
    public static final String PRICE = "price";
    public static final String PAYMENTS = "payments";
    public static final String HEADER = "header";
    public static final String DESCRIPTION = "description";
    public static final String ID = "id";
    public static final String STATE = "state";
    public static final String CATEGORY = "category";
    public static final String USER = "user";
    public static final String PHOTOS = "photos";

}

