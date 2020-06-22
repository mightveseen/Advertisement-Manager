package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    public AccountController(IChatService chatService, IMessageService messageService, IUserService userService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
    }

    //TODO : Change to Session user
    @GetMapping(value = "/chats")
    public List<ChatDto> getUserChats(@RequestParam(name = "userId") @Positive Long userId,
                                      @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                      @RequestParam(name = "max", defaultValue = "15") @Positive int max) {
        return chatService.getChats(userService.read(userId), page, max);
    }

    @GetMapping(value = "/chats/{id}")
    public List<MessageDto> getChatMessage(@PathVariable(name = "id") @Positive Long index,
                                           @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                           @RequestParam(name = "max", defaultValue = "15") @Positive int max) {
        return messageService.getMessages(index, page, max);
    }

    @PostMapping(value = "/chats/{id}")
    public ResponseEntity<Object> addMessageIntoChat(@PathVariable(name = "id") @Positive Long index,
                                                     @RequestBody @Validated(MessageDto.class) MessageDto message) {
        return new ResponseEntity<>(messageService.create(index, message), HttpStatus.OK);
    }

    //TODO : Change to Session user
    @DeleteMapping(value = "/chats/{id}")
    public ResponseEntity<Object> removeChat(@PathVariable(name = "id") @Positive Long index,
                                             @RequestParam(name = "userId") @Positive Long userId) {
        return new ResponseEntity<>(chatService.delete(index, userService.read(userId)), HttpStatus.OK);
    }

    @GetMapping(value = "/settings/profile")
    public UserDto getUserProfile(@RequestParam(name = "id") @Positive Long index) {
        return userService.getUser(index);
    }

    @PutMapping(value = "/settings/profile")
    public ResponseEntity<Object> updateUserProfile(@RequestBody @Validated(UserDto.class) UserDto user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }
}
