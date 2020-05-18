package com.senlainc.gitcourses.javatraining.petushokvaliantsin.controller;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service.ISystemUserService;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.dto.SystemUserDto;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.SystemUser;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.dto.IDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final IDtoMapper dtoMapper;
    private final ISystemUserService systemUserService;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, IDtoMapper dtoMapper, ISystemUserService systemUserService) {
        this.passwordEncoder = passwordEncoder;
        this.dtoMapper = dtoMapper;
        this.systemUserService = systemUserService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody @Validated(SystemUserDto.Create.class) SystemUserDto systemUserDto) {
        systemUserDto.setPassword(passwordEncoder.encode(systemUserDto.getPassword()));
        systemUserService.create(dtoMapper.map(systemUserDto, SystemUser.class));
    }
}
