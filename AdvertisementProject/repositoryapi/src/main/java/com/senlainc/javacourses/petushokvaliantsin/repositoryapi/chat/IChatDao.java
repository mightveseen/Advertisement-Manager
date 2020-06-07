package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import java.util.List;

public interface IChatDao extends IGenericDao<Chat, Long> {

    List<Chat> readAllUserChat(IPageParameter pageParameter, User user);

    void deleteUserChat(User user);
}
