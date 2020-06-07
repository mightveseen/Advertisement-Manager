package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;

import java.util.List;

public interface IMessageService {

    boolean create(Long chatIndex, Message object);

    boolean delete(Long index);

    boolean update(Message object);

    List<Message> readAll(Long chatIndex, int firstElement, int maxResult);
}
