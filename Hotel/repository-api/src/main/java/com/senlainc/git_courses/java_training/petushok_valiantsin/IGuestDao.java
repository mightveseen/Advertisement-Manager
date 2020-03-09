package com.senlainc.git_courses.java_training.petushok_valiantsin;

public interface IGuestDao extends ICommonDao<Guest, Integer> {
    Integer readSize();
}
