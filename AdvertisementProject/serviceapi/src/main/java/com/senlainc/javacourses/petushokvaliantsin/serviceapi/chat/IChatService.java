package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;

import java.util.List;

public interface IChatService {

    boolean create(ChatDto object);

    boolean update(Chat object);

    boolean delete(Long index, Long userIndex);

    Chat read(Long index);

    List<ChatDto> getChats(Long userIndex, int page, int maxResult);
}
