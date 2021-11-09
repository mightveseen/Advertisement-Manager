package com.senlainc.javacourses.petushokvaliantsin.service.api.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.ResultListDto;

public interface ChatService {

    boolean create(String username, Long advertisementIndex);

    boolean delete(Long index, String username);

    ResultListDto<ChatDto> readAll(String username, int page, int maxResult);
}
