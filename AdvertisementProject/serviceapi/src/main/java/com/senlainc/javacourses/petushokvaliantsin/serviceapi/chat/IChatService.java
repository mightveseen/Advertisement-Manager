package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import java.util.List;

public interface IChatService {

    boolean create(User user, Long advertisementIndex);

    boolean update(Chat object);

    boolean delete(Long index, Long userIndex);

    List<ChatDto> getChats(Long userIndex, int page, int maxResult);

    Long getSize(Long user);
}
