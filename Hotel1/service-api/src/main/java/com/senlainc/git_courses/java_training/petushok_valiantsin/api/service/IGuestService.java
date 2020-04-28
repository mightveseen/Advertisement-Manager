package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;

public interface IGuestService extends IGenericService<Guest, Long> {

    Long getNum();
}
