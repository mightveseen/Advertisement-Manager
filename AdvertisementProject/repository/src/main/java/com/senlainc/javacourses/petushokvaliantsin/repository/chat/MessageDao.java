package com.senlainc.javacourses.petushokvaliantsin.repository.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IMessageDao;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao extends AbstractDao<Message, Long> implements IMessageDao {
}
