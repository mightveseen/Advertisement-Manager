package com.senlainc.javacourses.petushokvaliantsin.service.api.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;

import java.util.List;

public interface MessageService {

    boolean create(String username, Long chatIndex, MessageDto object);

    List<MessageDto> readAll(String username, Long chatIndex, int firstElement, int maxResult);
}
