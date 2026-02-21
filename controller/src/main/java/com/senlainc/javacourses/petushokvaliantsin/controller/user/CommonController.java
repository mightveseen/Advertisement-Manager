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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class CommonController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    private final PaymentService paymentService;

    /**
     * Payment operation [Get operation history]
     */
    @ResponseStatus(OK)
    @GetMapping("/payments")
    public ResultListDto<PaymentDto> getUserPayments(@RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                                     @RequestParam(name = "max", defaultValue = "15") @Positive int max,
                                                     @NotNull Principal principal) {
        return ResultListDto.of(paymentService.readSize(principal.getName()), paymentService.readAll(principal.getName(), page, max));
    }

    /**
     * Chat operation [Show all, show chat message, add message, remove chat]
     */
    @ResponseStatus(OK)
    @GetMapping("/chats")
    public ResultListDto<ChatDto> getUserChats(@RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                               @RequestParam(name = "max", defaultValue = "15") @Positive int max,
                                               @NotNull Principal principal) {
        return chatService.readAll(principal.getName(), page, max);
    }

    @ResponseStatus(OK)
    @GetMapping("/chats/{id}")
    public List<MessageDto> getChatMessage(@PathVariable(name = "id") @Positive Long index,
                                           @RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                           @RequestParam(name = "max", defaultValue = "15") @Positive int max,
                                           @NotNull Principal principal) {
        return messageService.readAll(principal.getName(), index, page, max);
    }

    @ResponseStatus(OK)
    @PostMapping("/chats/{id}")
    public Boolean addMessageIntoChat(@PathVariable(name = "id") @Positive Long index,
                                      @RequestBody @Validated(MessageDto.Create.class) MessageDto message,
                                      @NotNull Principal principal) {
        return messageService.create(principal.getName(), index, message);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/chats/{id}")
    public Boolean removeChat(@PathVariable(name = "id") @Positive Long index,
                              @NotNull Principal principal) {
        return chatService.delete(index, principal.getName());
    }

    /**
     * Profile operation [Show/update profile information]
     */
    @ResponseStatus(OK)
    @GetMapping("/settings/profile")
    public UserDto getUserProfile(@NotNull Principal principal) {
        return userService.readByUsername(principal.getName());
    }

    @ResponseStatus(OK)
    @PutMapping("/settings/profile")
    public Boolean updateUserProfile(@RequestBody @Validated(UserDto.Update.class) UserDto user,
                                     @NotNull Principal principal) {
        return userService.update(principal.getName(), user);
    }
}
