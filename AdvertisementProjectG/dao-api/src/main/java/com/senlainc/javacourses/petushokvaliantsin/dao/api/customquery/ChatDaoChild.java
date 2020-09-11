package com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import java.util.List;

public interface ChatDaoChild {

    List<Chat> readAllUserChat(IPageParameter pageParameter, User user);

    Long readCountWithUser(User user);
}
