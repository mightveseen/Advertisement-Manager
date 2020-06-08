package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IMessageDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService extends AbstractService<Message, Long> implements IMessageService {

    private final IMessageDao messageDao;
    private final IChatService chatService;
    private final IUserService userService;

    @Autowired
    public MessageService(IMessageDao messageDao, IChatService chatService, IUserService userService) {
        this.messageDao = messageDao;
        this.chatService = chatService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean create(Long chatIndex, MessageDto object) {
        final Message message = dtoMapper.map(object, Message.class);
        message.setChat(chatService.read(chatIndex));
        message.setUser(userService.read(message.getUser().getId()));
        message.setDateTime(LocalDateTime.now());
        messageDao.create(message);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        messageDao.delete(messageDao.read(index));
        return true;
    }

    @Override
    @Transactional
    public boolean update(Message object) {
        messageDao.update(object);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> getMessages(Long chatIndex, int firstElement, int maxResult) {
        return dtoMapper.mapAll(messageDao.readAll(PageParameter.of(firstElement, maxResult), Message_.chat, chatService.read(chatIndex)),
                MessageDto.class);
    }
}
