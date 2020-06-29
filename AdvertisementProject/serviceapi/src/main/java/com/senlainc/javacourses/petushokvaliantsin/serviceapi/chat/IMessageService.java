package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;

import java.util.List;

public interface IMessageService {

    boolean create(Long chatIndex, MessageDto message);

    List<MessageDto> getMessages(Long chatIndex, int firstElement, int maxResult);
}
