package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatService extends AbstractService implements IChatService {

    private static final Logger LOGGER = LogManager.getLogger(ChatService.class);
    private final IChatDao chatDao;
    private final IUserDao userDao;
    private final IAdvertisementDao advertisementDao;

    @Autowired
    public ChatService(IChatDao chatDao, IUserDao userDao, IAdvertisementDao advertisementDao) {
        this.chatDao = chatDao;
        this.userDao = userDao;
        this.advertisementDao = advertisementDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long advertisementIndex) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        final User activeUser = userDao.readByUserCred(username).orElseThrow();
        final User chosenUser = advertisement.getUser();
        checkChatExist(activeUser, chosenUser);
        chatDao.create(new Chat(advertisement.getHeader(), String.format("%s create chat", activeUser.getFirstName()),
                LocalDateTime.now(), getSetUser(activeUser, chosenUser)));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index, String username) {
        final Chat chat = chatDao.read(index);
        chat.getUsers().remove(userDao.readByUserCred(username));
        if (chat.getUsers().isEmpty()) {
            chatDao.delete(chat);
        } else {
            chatDao.update(chat);
        }
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> readAll(String username, int page, int maxResult) {
        final IPageParameter pageParameter = PageParameter.of(page, maxResult, Sort.Direction.DESC.name(), Chat_.updateDateTime);
        final List<ChatDto> result = dtoMapper.mapAll(chatDao.readAllUserChat(pageParameter, userDao.readByUserCred(username).orElseThrow()), ChatDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize(String username) {
        return chatDao.readCountWithUser(userDao.readByUserCred(username).orElseThrow());
    }

    private Set<User> getSetUser(User activeUser, User chosenUser) {
        final Set<User> users = new HashSet<>();
        users.add(chosenUser);
        users.add(activeUser);
        return users;
    }

    private void checkChatExist(User activeUser, User chosenUser) {
        if (activeUser.getChats().stream().anyMatch(i -> i.getUsers().stream().anyMatch(j -> j.getId().equals(chosenUser.getId())))) {
            throw new EntityAlreadyExistException(EnumException.CHAT_EXIST.getMessage());
        }
    }
}
