package com.senlainc.javacourses.petushokvaliantsin.dao.api.chat;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.ChatDaoChild;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatDao extends ChatDaoChild, CrudRepository<Chat, Long> {

}
