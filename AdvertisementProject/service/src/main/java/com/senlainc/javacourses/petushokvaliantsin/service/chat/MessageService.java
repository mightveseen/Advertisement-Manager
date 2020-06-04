package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IMessageDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService extends AbstractService<Message, Long> implements IMessageService {

    private final IMessageDao messageDao;

    @Autowired
    public MessageService(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public boolean create(Message object) {
        messageDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        messageDao.delete(messageDao.read(index));
        return true;
    }

    @Override
    public boolean update(Message object) {
        messageDao.update(object);
        return true;
    }

    @Override
    public Message read(Long index) {
        return messageDao.read(index);
    }

    @Override
    public List<Message> readAll(int firstElement, int maxResult) {
        return messageDao.readAll(PageParameter.of(firstElement, maxResult));
    }
}
