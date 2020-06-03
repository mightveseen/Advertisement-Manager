package com.senlainc.javacourses.petushokvaliantsin.repositoryapi;

import com.senlainc.javacourses.petushokvaliantsin.model.State;

public interface IStateDao {

    State read(String description);
}
