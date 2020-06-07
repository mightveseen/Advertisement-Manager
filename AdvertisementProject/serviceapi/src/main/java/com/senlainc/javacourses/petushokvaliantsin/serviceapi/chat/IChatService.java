package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import java.util.List;

public interface IChatService {

    boolean create(Long userIndex, User accountIndex);

    boolean delete(Long index, User user);

    Chat read(Long index);

    List<ChatDto> getChats(User user, int page, int maxResult);
}
