package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.AdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.chat.ChatDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.ResultListDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.chat.ChatService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl extends AbstractService implements ChatService {

    private static final Logger LOGGER = LogManager.getLogger(ChatServiceImpl.class);
    private final ChatDao chatDao;
    private final UserDao userDao;
    private final AdvertisementDao advertisementDao;

    @Override
    @Transactional
    public boolean create(String username, Long advertisementIndex) {
        final Advertisement advertisement = advertisementDao.findById(advertisementIndex).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(0, 0, advertisementIndex)));
        final User activeUser = userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username)));
        final User chosenUser = advertisement.getUser();
        checkChatExist(activeUser, chosenUser);
        chatDao.save(new Chat(advertisement.getHeader(), String.format("%s create chat", activeUser.getFirstName()),
                LocalDateTime.now(), new HashSet<>(Set.of(activeUser, chosenUser))));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index, String username) {
        final Chat chat = chatDao.findById(index).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(3, 0, index)));
        chat.getUsers().remove(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username))));
        if (chat.getUsers().isEmpty()) {
            chatDao.delete(chat);
        } else {
            chatDao.save(chat);
        }
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public ResultListDto<ChatDto> readAll(String username, int page, int maxResult) {
        final Pageable pageParameter = PageRequest.of(page, maxResult, Sort.Direction.DESC, Chat_.UPDATE_DATE_TIME);
        final User user = userDao.readByUserCred(username, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username)));
        final Long resultSize = chatDao.countAllByUser(user);
        final List<ChatDto> result = dtoMapper.mapAll(chatDao.readAllByUser(user, pageParameter), ChatDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return ResultListDto.of(resultSize, result);
    }

    private void checkChatExist(User activeUser, User chosenUser) {
        if (activeUser.getChats().stream().anyMatch(i -> i.getUsers().stream().anyMatch(j -> j.getId().equals(chosenUser.getId())))) {
            throw new EntityAlreadyExistException(EnumException.CHAT_EXIST.getMessage());
        }
    }
}
