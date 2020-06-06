package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    private final IChatService chatService;
    private final IMessageService messageService;
    private final IUserService userService;
    private final IDtoMapper dtoMapper;

    @Autowired
    public AccountController(IChatService chatService, IMessageService messageService, IUserService userService, IDtoMapper dtoMapper) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping(value = "/messages")
    public List<ChatDto> getUserChats(@RequestParam(name = "id") Long index,
                                      @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                      @RequestParam(name = "max", defaultValue = "15") @Positive int max) {
//        return dtoMapper.mapAll(chatService.readAll(index, page, max), ChatDto.class);
        return null;
    }
}
