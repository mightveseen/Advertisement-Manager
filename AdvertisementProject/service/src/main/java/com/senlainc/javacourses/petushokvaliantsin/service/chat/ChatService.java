package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
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

    //TODO : Fix
    @Override
    @Transactional
    public boolean create(User user, Long advertisementIndex) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        final User secondUser = advertisement.getUser();
        if (userDao.read((long) 1).getChats().stream().anyMatch(i -> i.getUsers().stream().anyMatch(j -> j.getId().equals(secondUser.getId())))) {
            throw new EntityAlreadyExistException("Chat with this user already exist");
        }
        final Set<User> users = new HashSet<>();
        users.add(secondUser);
        users.add(userDao.read((long) 1));
        chatDao.create(new Chat(advertisement.getHeader(), userDao.read((long) 1).getFirstName() + " create chat", LocalDateTime.now(), users));
        return true;
    }

    @Override
    @Transactional
    public boolean update(Chat object) {
        chatDao.read(object.getId());
        chatDao.update(object);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index, Long userIndex) {
        final Chat chat = chatDao.read(index);
        chat.getUsers().remove(userDao.read(userIndex));
        if (chat.getUsers().isEmpty()) {
            chatDao.delete(chat);
        } else {
            chatDao.update(chat);
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> getChats(Long userIndex, int page, int maxResult) {
        final IPageParameter pageParameter = PageParameter.of(page, maxResult, Sort.Direction.DESC.name(), Chat_.updateDateTime);
        return dtoMapper.mapAll(chatDao.readAllUserChat(pageParameter, userDao.read(userIndex)), ChatDto.class);
    }

    //TODO : Fix
    @Override
    @Transactional(readOnly = true)
    public Long getSize(Long user) {
        return chatDao.readCountWithUser(userDao.read(user));
    }
}
