package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Override
    public boolean create(Message object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(Message object) {
        return false;
    }

    @Override
    public Message read(Long index) {
        return null;
    }

    @Override
    public List<Message> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public <C> List<Message> readAll(int firstElement, int maxResult, SingularAttribute<Message, C> sortField) {
        return null;
    }
}
