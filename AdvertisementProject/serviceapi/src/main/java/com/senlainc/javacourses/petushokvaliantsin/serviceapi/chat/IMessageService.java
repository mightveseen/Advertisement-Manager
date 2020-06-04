package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IMessageService extends IGenericService<Message, Long> {

    boolean create(Message object);

    boolean delete(Long index);

    boolean update(Message object);
}
