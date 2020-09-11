package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    private final UserService userService;
    private final TokenMapper tokenMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(UserService userService, TokenMapper tokenMapper,
                                    AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenMapper = tokenMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Boolean> createUser(@RequestBody @Validated({AccountDto.Create.class, UserCredDto.Create.class, UserDto.Create.class}) AccountDto accountDto) {
        return new ResponseEntity<>(userService.create(accountDto), HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Boolean> login(@RequestBody @Validated(UserCredDto.Create.class) UserCredDto userCredDto, HttpServletResponse response) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCredDto.getUsername(), userCredDto.getPassword());
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setHeader(tokenMapper.getTokenHeader(), tokenMapper.generateToken(authentication));
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }
}
