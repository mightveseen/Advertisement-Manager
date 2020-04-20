package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import org.springframework.stereotype.Repository;

@Repository
public class GuestDao extends AbstractDao<Guest, Long> implements IGuestDao {

}
