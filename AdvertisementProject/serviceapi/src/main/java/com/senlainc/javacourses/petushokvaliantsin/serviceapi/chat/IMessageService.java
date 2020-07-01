package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;

import java.util.List;

public interface IMessageService {

    boolean create(String username, Long chatIndex, MessageDto message);

    List<MessageDto> getMessages(String username, Long chatIndex, int firstElement, int maxResult);
}
