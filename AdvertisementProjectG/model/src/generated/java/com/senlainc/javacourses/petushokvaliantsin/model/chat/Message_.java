package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Message, Chat> chat;
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, String> text;
	public static volatile SingularAttribute<Message, User> user;

	public static final String DATE_TIME = "dateTime";
	public static final String CHAT = "chat";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String USER = "user";

}

