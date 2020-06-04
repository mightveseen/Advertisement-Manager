package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IChatService extends IGenericService<Chat, Long> {

    boolean create(Chat object);

    boolean delete(Long index);

    boolean update(Chat object);
}
