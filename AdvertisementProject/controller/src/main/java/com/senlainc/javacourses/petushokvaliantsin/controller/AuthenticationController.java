package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserCredService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final IUserCredService userCredService;
    private final PasswordEncoder passwordEncoder;
    private final TokenMapper tokenMapper;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(IUserCredService userCredService, PasswordEncoder passwordEncoder, TokenMapper tokenMapper,
                                    AuthenticationManager authenticationManager) {
        this.userCredService = userCredService;
        this.passwordEncoder = passwordEncoder;
        this.tokenMapper = tokenMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Boolean> createUser(@RequestBody @Validated(UserCredDto.Create.class) UserCredDto userCredDto) {
        userCredDto.setPassword(passwordEncoder.encode(userCredDto.getPassword()));
        return new ResponseEntity<>(userCredService.create(userCredDto), HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public void authenticateUser(@RequestBody @Validated(UserCredDto.Create.class) UserCredDto userCredDto) {
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userCredDto.getLogin(), userCredDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(token));
        tokenMapper.generateToken(SecurityContextHolder.getContext().getAuthentication());
    }
}
