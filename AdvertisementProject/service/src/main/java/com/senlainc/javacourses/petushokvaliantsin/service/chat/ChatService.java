package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService extends AbstractService<Chat, Long> implements IChatService {
    @Override
    public boolean create(Chat object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(Chat object) {
        return false;
    }

    @Override
    public Chat read(Long index) {
        return null;
    }

    @Override
    public List<Chat> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public List<Chat> readAll(int firstElement, int maxResult, String sortField) {
        return null;
    }
}
