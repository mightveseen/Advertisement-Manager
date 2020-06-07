package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //TODO : Change to Session user
    @GetMapping(value = "/chats")
    public List<ChatDto> getUserChats(@RequestParam(name = "userId") @Positive Long userId,
                                      @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                      @RequestParam(name = "max", defaultValue = "15") @Positive int max) {
        return dtoMapper.mapAll(chatService.readAll(userService.read(userId), page, max), ChatDto.class);
    }

    @GetMapping(value = "/chats/{id}")
    public List<MessageDto> getChatMessage(@PathVariable(name = "id") @Positive Long index,
                                           @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                           @RequestParam(name = "max", defaultValue = "15") @Positive int max) {
        return dtoMapper.mapAll(messageService.readAll(index, page, max), MessageDto.class);
    }

    @PostMapping(value = "/chats/{id}")
    public boolean addMessageIntoChat(@PathVariable(name = "id") @Positive Long index,
                                      @RequestBody @Validated(MessageDto.class) MessageDto message) {
        return messageService.create(index, dtoMapper.map(message, Message.class));
    }

    //TODO : Change to Session user
    @DeleteMapping(value = "/chats/{id}")
    public boolean removeChat(@PathVariable(name = "id") @Positive Long index,
                              @RequestParam(name = "userId") @Positive Long userId) {
        return chatService.delete(index, userService.read(userId));
    }
}
