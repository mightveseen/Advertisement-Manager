package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService extends AbstractService<Chat, Long> implements IChatService {

    private final IChatDao chatDao;
    private final IUserService userService;

    @Autowired
    public ChatService(IChatDao chatDao, IUserService userService) {
        this.chatDao = chatDao;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean create(ChatDto object) {
        chatDao.create(dtoMapper.map(object, Chat.class));
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index, User user) {
        final Chat chat = chatDao.read(index);
        chat.getUsers().remove(userService.read(user.getId()));
        chatDao.update(chat);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Chat read(Long index) {
        return chatDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> getChats(User user, int page, int maxResult) {
        return dtoMapper.mapAll(chatDao.readAllUserChat(PageParameter.of(page, maxResult, Sort.Direction.DESC.name(), Chat_.updateDateTime), user),
                ChatDto.class);
    }
}
