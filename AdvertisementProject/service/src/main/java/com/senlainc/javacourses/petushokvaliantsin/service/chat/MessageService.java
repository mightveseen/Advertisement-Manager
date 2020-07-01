package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IMessageDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService extends AbstractService<Message, Long> implements IMessageService {

    private final IMessageDao messageDao;
    private final IChatDao chatDao;
    private final IUserDao userDao;

    @Autowired
    public MessageService(IMessageDao messageDao, IChatDao chatDao, IUserDao userDao) {
        this.messageDao = messageDao;
        this.chatDao = chatDao;
        this.userDao = userDao;
    }

    //TODO : Check authority
    @Override
    @Transactional
    public boolean create(Long chatIndex, MessageDto object) {
        final Message message = dtoMapper.map(object, Message.class);
        final Chat chat = chatDao.read(chatIndex);
        message.setChat(chat);
        message.setUser(userDao.read(message.getUser().getId()));
        message.setDateTime(LocalDateTime.now());
        messageDao.create(message);
        chat.setUpdateDateTime(LocalDateTime.now());
        chat.setLastMessage(message.getUser().getFirstName() + ": " + message.getText().substring(0, 20) + "...");
        chatDao.update(chat);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> getMessages(Long chatIndex, int firstElement, int maxResult) {
        return dtoMapper.mapAll(messageDao.readAll(PageParameter.of(firstElement, maxResult), Message_.chat, chatDao.read(chatIndex)),
                MessageDto.class);
    }
}
