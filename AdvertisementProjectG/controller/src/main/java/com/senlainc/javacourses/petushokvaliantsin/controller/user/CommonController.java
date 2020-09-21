package com.senlainc.javacourses.petushokvaliantsin.controller.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.ResultListDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.service.api.chat.ChatService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.chat.MessageService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.payment.PaymentService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserService;
import lombok.RequiredArgsConstructor;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "account")
public class CommonController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    private final PaymentService paymentService;

    /**
     * Payment operation [Get operation history]
     */
    @GetMapping(value = "/payments")
    public ResultListDto<PaymentDto> getUserPayments(@RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                                     @RequestParam(name = "max", defaultValue = "15") @Positive int max,
                                                     @NotNull Principal principal) {
        return new ResultListDto<>(paymentService.readSize(principal.getName()), paymentService.readAll(principal.getName(), page, max));
    }

    /**
     * Chat operation [Show all, show chat message, add message, remove chat]
     */
    @GetMapping(value = "/chats")
    public ResultListDto<ChatDto> getUserChats(@RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                               @RequestParam(name = "max", defaultValue = "15") @Positive int max,
                                               @NotNull Principal principal) {
        return new ResultListDto<>(chatService.readSize(principal.getName()), chatService.readAll(principal.getName(), page, max));
    }

    @GetMapping(value = "/chats/{id}")
    public List<MessageDto> getChatMessage(@PathVariable(name = "id") @Positive Long index,
                                           @RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                           @RequestParam(name = "max", defaultValue = "15") @Positive int max,
                                           @NotNull Principal principal) {
        return messageService.readAll(principal.getName(), index, page, max);
    }

    @PostMapping(value = "/chats/{id}")
    public ResponseEntity<Boolean> addMessageIntoChat(@PathVariable(name = "id") @Positive Long index,
                                                      @RequestBody @Validated(MessageDto.Create.class) MessageDto message,
                                                      @NotNull Principal principal) {
        return new ResponseEntity<>(messageService.create(principal.getName(), index, message), HttpStatus.OK);
    }

    @DeleteMapping(value = "/chats/{id}")
    public ResponseEntity<Boolean> removeChat(@PathVariable(name = "id") @Positive Long index,
                                              @NotNull Principal principal) {
        return new ResponseEntity<>(chatService.delete(index, principal.getName()), HttpStatus.OK);
    }

    /**
     * Profile operation [Show/update profile information]
     */
    @GetMapping(value = "/settings/profile")
    public ResponseEntity<UserDto> getUserProfile(@NotNull Principal principal) {
        return new ResponseEntity<>(userService.readByUsername(principal.getName()), HttpStatus.OK);
    }

    @PutMapping(value = "/settings/profile")
    public ResponseEntity<Boolean> updateUserProfile(@RequestBody @Validated(UserDto.Update.class) UserDto user,
                                                     @NotNull Principal principal) {
        return new ResponseEntity<>(userService.update(principal.getName(), user), HttpStatus.OK);
    }
}
