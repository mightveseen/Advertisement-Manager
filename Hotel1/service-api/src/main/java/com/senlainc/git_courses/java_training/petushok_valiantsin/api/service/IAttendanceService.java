package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;

import java.util.List;

public interface IAttendanceService extends IGenericService<Attendance, Long> {

    void changePrice(long index, double price);

    List<Attendance> readAllSorted(String parameter, int firstElement, int maxResult);
}
