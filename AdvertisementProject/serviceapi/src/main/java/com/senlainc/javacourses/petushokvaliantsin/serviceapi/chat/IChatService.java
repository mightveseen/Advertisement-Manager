package com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;

import java.util.List;

public interface IChatService {

    boolean create(String username, Long advertisementIndex);

    boolean delete(Long index, String username);

    List<ChatDto> getChats(String username, int page, int maxResult);

    Long getSize(String username);
}
