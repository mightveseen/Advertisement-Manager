package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AdvertisementCategory.class)
public abstract class AdvertisementCategory_ {

    public static volatile SetAttribute<AdvertisementCategory, Advertisement> advertisements;
    public static volatile SingularAttribute<AdvertisementCategory, String> description;
    public static volatile SingularAttribute<AdvertisementCategory, Long> id;

    public static final String ADVERTISEMENTS = "advertisements";
    public static final String DESCRIPTION = "description";
    public static final String ID = "id";

}

