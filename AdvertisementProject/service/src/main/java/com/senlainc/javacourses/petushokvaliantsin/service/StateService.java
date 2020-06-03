package com.senlainc.javacourses.petushokvaliantsin.service;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService implements IStateService {

    private final IStateDao stateDao;

    @Autowired
    public StateService(IStateDao stateDao) {
        this.stateDao = stateDao;
    }

    @Override
    public State read(String description) {
        return stateDao.read(description);
    }
}
