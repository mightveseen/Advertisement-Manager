package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatService extends AbstractService<Chat, Long> implements IChatService {

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
        final User activeUser = userDao.readByUserCred(username);
        final User chosenUser = advertisement.getUser();
        if (activeUser.getChats().stream().anyMatch(i -> i.getUsers().stream().anyMatch(j -> j.getId().equals(chosenUser.getId())))) {
            throw new EntityAlreadyExistException(EnumException.CHAT_EXIST.getMessage());
        }
        final Set<User> users = new HashSet<>();
        users.add(chosenUser);
        users.add(activeUser);
        chatDao.create(new Chat(advertisement.getHeader(), activeUser.getFirstName() + " create chat", LocalDateTime.now(), users));
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
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> getChats(String username, int page, int maxResult) {
        final IPageParameter pageParameter = PageParameter.of(page, maxResult, Sort.Direction.DESC.name(), Chat_.updateDateTime);
        return dtoMapper.mapAll(chatDao.readAllUserChat(pageParameter, userDao.readByUserCred(username)), ChatDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getSize(String username) {
        return chatDao.readCountWithUser(userDao.readByUserCred(username));
    }
}
