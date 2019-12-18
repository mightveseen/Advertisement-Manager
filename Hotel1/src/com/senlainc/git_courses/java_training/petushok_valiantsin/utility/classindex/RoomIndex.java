package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex;

public class RoomIndex {
    private static int index = 0;

    public RoomIndex() {
        index++;
    }

    public int getIndex() {
        return index;
    }
}
