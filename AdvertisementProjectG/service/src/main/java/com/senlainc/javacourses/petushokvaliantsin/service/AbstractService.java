package com.senlainc.javacourses.petushokvaliantsin.service;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {

    protected static final String[] CLASSES = {"Advertisement", "State", "User", "Chat", "Category", "Photo", "Payment", "Type of payment"};
    protected static final String[] FIELDS = {"id", "description", "username"};
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

    protected String entityNotExistMessage(int classId, int fieldId, Object field) {
        return String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[classId], FIELDS[fieldId], field);
    }
}
