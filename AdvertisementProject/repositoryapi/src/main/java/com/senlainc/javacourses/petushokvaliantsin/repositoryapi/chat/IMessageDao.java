package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageDao extends CrudRepository<Message, Long> {

    List<Message> readAllByChat(Pageable pageable, Chat chat);
}
