package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex;

public class OrderIndex {
    private static int index = 0;

    public OrderIndex() {
        index++;
    }

    public int getIndex() {
        return index;
    }
}
