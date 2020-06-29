package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService extends AbstractService<Chat, Long> implements IChatService {

    private final IChatDao chatDao;
    private final IUserDao userDao;

    @Autowired
    public ChatService(IChatDao chatDao, IUserDao userDao) {
        this.chatDao = chatDao;
        this.userDao = userDao;
    }

    //TODO : Check chat
    @Override
    @Transactional
    public boolean create(ChatDto object) {
        final Chat chat = dtoMapper.map(object, Chat.class);
        chat.setUpdateDateTime(LocalDateTime.now());
        chat.setLastMessage(object.getUsers().toArray()[0] + " create chat");
        chatDao.create(chat);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Chat object) {
        chatDao.read(object.getId());
        chatDao.update(object);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index, Long userIndex) {
        final Chat chat = chatDao.read(index);
        chat.getUsers().remove(userDao.read(userIndex));
        if (chat.getUsers().isEmpty()) {
            chatDao.delete(chat);
        } else {
            chatDao.update(chat);
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> getChats(Long userIndex, int page, int maxResult) {
        final IPageParameter pageParameter = PageParameter.of(page, maxResult, Sort.Direction.DESC.name(), Chat_.updateDateTime);
        return dtoMapper.mapAll(chatDao.readAllUserChat(pageParameter, userDao.read(userIndex)), ChatDto.class);
    }
}
