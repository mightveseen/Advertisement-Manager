package com.senlainc.javacourses.petushokvaliantsin.dao.api;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StateDao extends CrudRepository<State, Long> {

    Optional<State> readByDescription(String description);
}
