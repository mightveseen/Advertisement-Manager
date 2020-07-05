package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IMessageDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IMessageService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService extends AbstractService implements IMessageService {

    private static final Logger LOGGER = LogManager.getLogger(MessageService.class);
    private final IMessageDao messageDao;
    private final IChatDao chatDao;
    private final IUserDao userDao;

    @Autowired
    public MessageService(IMessageDao messageDao, IChatDao chatDao, IUserDao userDao) {
        this.messageDao = messageDao;
        this.chatDao = chatDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long chatIndex, MessageDto object) {
        final Chat chat = chatDao.read(chatIndex);
        final User activeUser = userDao.readByUserCred(username);
        checkPermission(chat, activeUser);
        messageDao.create(createMessage(object, chat, activeUser));
        chatDao.update(createChat(chat, activeUser, object.getText()));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> readAll(String username, Long chatIndex, int firstElement, int maxResult) {
        final Chat chat = chatDao.read(chatIndex);
        checkPermission(chat, userDao.readByUserCred(username));
        final List<MessageDto> result = dtoMapper.mapAll(messageDao.readAll(PageParameter.of(firstElement, maxResult), Message_.chat, chatDao.read(chatIndex)),
                MessageDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    private Message createMessage(MessageDto messageDto, Chat chat, User activeUser) {
        final Message message = dtoMapper.map(messageDto, Message.class);
        message.setChat(chat);
        message.setUser(activeUser);
        message.setDateTime(LocalDateTime.now());
        return message;
    }

    private Chat createChat(Chat chat, User activeUser, String text) {
        chat.setUpdateDateTime(LocalDateTime.now());
        chat.setLastMessage(activeUser.getFirstName() + ": " + text.substring(0, 20) + "...");
        return chat;
    }

    private void checkPermission(Chat chat, User activeUser) {
        if (chat.getUsers().stream().noneMatch(i -> i.getId().equals(activeUser.getId()))) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }
}
