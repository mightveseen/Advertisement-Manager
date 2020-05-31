package com.senlainc.javacourses.petushokvaliantsin.service;

import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractService<E, K extends Serializable> implements IGenericService<E, K> {

    protected ISingularMapper singularMapper;

    @Autowired
    public void setSingularMapper(ISingularMapper singularMapper) {
        this.singularMapper = singularMapper;
    }
}