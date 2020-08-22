package com.senlainc.javacourses.petushokvaliantsin.repositoryapi;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import org.springframework.data.repository.CrudRepository;

public interface IStateDao extends CrudRepository<State, Long> {

    State readByDescription(String description);
}
