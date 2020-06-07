package com.senlainc.javacourses.petushokvaliantsin.controller;


import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final IUserService userService;
    private final IChatService chatService;
    private final IDtoMapper dtoMapper;

    public UserController(IDtoMapper dtoMapper, IUserService userService, IChatService chatService) {
        this.dtoMapper = dtoMapper;
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable(name = "id") @Positive Long index) {
        return dtoMapper.map(userService.read(index), UserDto.class);
    }

    @PostMapping(value = "/{id}")
    public boolean createChatWithUser(@PathVariable(name = "id") @Positive Long index,
                                      @RequestParam(name = "id") @Positive Long accountId) {
        return chatService.create(index, accountId);
    }
}
