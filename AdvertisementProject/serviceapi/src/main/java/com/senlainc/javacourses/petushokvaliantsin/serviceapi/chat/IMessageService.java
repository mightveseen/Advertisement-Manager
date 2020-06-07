package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;

import java.util.List;

public interface IMessageService {

    boolean create(Long chatIndex, MessageDto message);

    boolean delete(Long index);

    boolean update(Message object);

    List<MessageDto> getMessages(Long chatIndex, int firstElement, int maxResult);
}
