package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Chat.class)
public abstract class Chat_ {

	public static volatile SingularAttribute<Chat, String> name;
	public static volatile SingularAttribute<Chat, String> lastMessage;
	public static volatile SetAttribute<Chat, Message> messages;
	public static volatile SingularAttribute<Chat, Long> id;
	public static volatile SingularAttribute<Chat, LocalDateTime> updateDateTime;
	public static volatile SetAttribute<Chat, User> users;

	public static final String NAME = "name";
	public static final String LAST_MESSAGE = "lastMessage";
	public static final String MESSAGES = "messages";
	public static final String ID = "id";
	public static final String UPDATE_DATE_TIME = "updateDateTime";
	public static final String USERS = "users";

}

