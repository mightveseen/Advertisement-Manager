package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final TokenMapper tokenMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Boolean> createUser(@RequestBody @Validated({AccountDto.Create.class, UserCredDto.Create.class, UserDto.Create.class}) AccountDto accountDto) {
        return new ResponseEntity<>(userService.create(accountDto), HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Boolean> login(@RequestBody @Validated(UserCredDto.Create.class) UserCredDto userCredDto, HttpServletResponse response) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userCredDto.username(),
                userCredDto.password());
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        response.setHeader(tokenMapper.getTokenHeader(), tokenMapper.generateToken(authentication));
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }
}
