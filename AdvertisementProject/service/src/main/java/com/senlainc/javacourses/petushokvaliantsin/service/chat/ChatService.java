package com.senlainc.javacourses.petushokvaliantsin.service.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat.IChatDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
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
@Transactional
public class ChatService extends AbstractService<Chat, Long> implements IChatService {

    private final IChatDao chatDao;
    private final IUserService userService;

    @Autowired
    public ChatService(IChatDao chatDao, IUserService userService) {
        this.chatDao = chatDao;
        this.userService = userService;
    }

    @Override
    public boolean create(Long userIndex, User accountUser) {
        final Set<User> chatUsers = new HashSet<>();
        final User user = userService.read(userIndex);
        final Chat chat = new Chat(user.getFirstName(), accountUser.getFirstName() + " create chat", LocalDateTime.now());
        chatUsers.add(user);
        chatUsers.add(accountUser);
        chat.setUsers(chatUsers);
        chatDao.create(chat);
        return true;
    }

    @Override
    public boolean delete(Long index, User user) {
        final Chat chat = chatDao.read(index);
        chat.getUsers().remove(chat.getUsers().stream().filter(i -> i.getId().equals(user.getId())).findFirst().orElse(null));
        chatDao.update(chat);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Chat read(Long index) {
        return chatDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> readAll(User user, int page, int maxResult) {
        return chatDao.readAllUserChat(PageParameter.of(page, maxResult, Sort.Direction.DESC.name(), Chat_.updateDateTime), user);
    }
}
