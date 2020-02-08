package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.collection;

import java.util.Comparator;

public interface ICustomList<T> {
    void add(T element);

    void add(int index, T element);

    void addAll(ICustomList<T> list);

    void push(int index, T element);

    T get(int index);

    T[] getAll();

    void remove(int index);

    int size();

    void sort(Comparator<T> comparator);
}
