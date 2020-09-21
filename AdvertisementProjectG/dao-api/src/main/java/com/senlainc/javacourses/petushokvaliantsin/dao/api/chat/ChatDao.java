package com.senlainc.javacourses.petushokvaliantsin.dao.api.chat;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatDao extends CrudRepository<Chat, Long> {

    @EntityGraph(value = GraphProperty.CHAT_DEFAULT)
    @Query(value = "select c from Chat c left join c.users u where u = ?1")
    List<Chat> readAllByUser(User user, Pageable pageable);

    @Query(value = "select count(c) from Chat c left join c.users u where u = ?1")
    Long countAllByUser(User user);
}
