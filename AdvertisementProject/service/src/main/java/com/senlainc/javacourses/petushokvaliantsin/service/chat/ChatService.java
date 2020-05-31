package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService extends AbstractService<Chat, Long> implements IChatService {

    private final IChatDao chatDao;

    @Autowired
    public ChatService(IChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @Override
    public boolean create(Chat object) {
        chatDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        chatDao.delete(chatDao.read(index));
        return true;
    }

    @Override
    public boolean update(Chat object) {
        chatDao.update(object);
        return true;
    }

    @Override
    public Chat read(Long index) {
        return chatDao.read(index);
    }

    @Override
    public List<Chat> readAll(int firstElement, int maxResult, String direction) {
        return chatDao.readAll(PageParameter.of(firstElement, maxResult, direction));
    }
}
