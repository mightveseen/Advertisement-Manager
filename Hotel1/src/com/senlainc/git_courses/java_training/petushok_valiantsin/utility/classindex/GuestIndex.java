package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex;

public class GuestIndex {
    private static int index = 0;

    public GuestIndex() {
        index++;
    }

    public int getIndex() {
        return index;
    }
}
