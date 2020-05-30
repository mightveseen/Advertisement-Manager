package com.senlainc.javacourses.petushokvaliantsin.repository.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDao extends AbstractDao<Chat, Long> implements IChatDao {
}
