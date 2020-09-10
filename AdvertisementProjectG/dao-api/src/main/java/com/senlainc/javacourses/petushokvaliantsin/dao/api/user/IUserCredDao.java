package com.senlainc.javacourses.petushokvaliantsin.dao.api.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserCredDao extends CrudRepository<UserCred, Long> {

    @EntityGraph(attributePaths = {"user"})
    Optional<UserCred> readByUsername(String username);
}
