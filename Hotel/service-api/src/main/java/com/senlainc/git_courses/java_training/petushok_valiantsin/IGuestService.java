package com.senlainc.git_courses.java_training.petushok_valiantsin;

import java.time.LocalDate;
import java.util.List;

public interface IGuestService {
    void add(String firstName, String lastName, LocalDate birthday);

    void delete(int index);

    int num();

    List<Guest> show();
}
