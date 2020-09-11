package com.senlainc.javacourses.petushokvaliantsin.dao.api.chat;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.ChatDaoCustomQuery;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatDao extends ChatDaoCustomQuery, CrudRepository<Chat, Long> {

}
