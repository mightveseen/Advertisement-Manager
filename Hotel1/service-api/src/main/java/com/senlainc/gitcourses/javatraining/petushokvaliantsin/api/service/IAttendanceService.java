package com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Attendance;

import java.util.List;

public interface IAttendanceService extends IGenericService<Attendance, Long> {

    void changePrice(long index, double price);

    List<Attendance> readAllSorted(String parameter, int firstElement, int maxResult);
}
