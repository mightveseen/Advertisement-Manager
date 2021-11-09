package com.senlainc.javacourses.petushokvaliantsin.dao.api;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface StateDao extends Repository<State, Long> {

    Optional<State> readByDescription(String description);
}
