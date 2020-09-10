package com.senlainc.javacourses.petushokvaliantsin.service;

import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {

    protected static final String[] CLASSES = {"Advertisement", "State", "User", "Chat"};
    protected static final String[] CLASS_FIELDS = {"id", "description", "username"};
    protected ISingularMapper singularMapper;
    protected IDtoMapper dtoMapper;

    @Autowired
    public void setSingularMapper(ISingularMapper singularMapper) {
        this.singularMapper = singularMapper;
    }

    @Autowired
    public void setDtoMapper(IDtoMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }
}