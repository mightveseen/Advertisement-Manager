package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.chat.ChatDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.chat.MessageDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.chat.MessageService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl extends AbstractService implements MessageService {

    private static final Logger LOGGER = LogManager.getLogger(MessageServiceImpl.class);
    private final MessageDao messageDao;
    private final ChatDao chatDao;
    private final UserDao userDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, ChatDao chatDao, UserDao userDao) {
        this.messageDao = messageDao;
        this.chatDao = chatDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long chatIndex, MessageDto object) {
        final Chat chat = chatDao.findById(chatIndex).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(3, 0, chatIndex)));
        final User activeUser = userDao.readByUserCred(username, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username)));
        checkPermission(chat, activeUser);
        messageDao.save(createMessage(object, chat, activeUser));
        chatDao.save(createChat(chat, activeUser, object.getText()));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> readAll(String username, Long chatIndex, int firstElement, int maxResult) {
        final Chat chat = chatDao.findById(chatIndex).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(3, 0, chatIndex)));
        checkPermission(chat, userDao.readByUserCred(username, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username))));
        final List<MessageDto> result = dtoMapper.mapAll(
                messageDao.readAllByChat(PageRequest.of(firstElement, maxResult), chat), MessageDto.class);
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
