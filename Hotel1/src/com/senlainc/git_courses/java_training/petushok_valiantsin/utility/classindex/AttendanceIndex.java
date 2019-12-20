package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex;

public class AttendanceIndex {
    private static int index = 0;

    public AttendanceIndex() {
        index++;
    }

    public int getIndex() {
        return index;
    }
}
