package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility;

import java.util.Arrays;
import java.util.Comparator;

public class MyList<T> {
    private final int INIT_SIZE = 10;
    private int index = 0;
    private T[] objArray = (T[]) new Object[INIT_SIZE];

    public MyList() {
    }

    public MyList(MyList<T> clazz) {
        System.arraycopy(clazz, 0, objArray, 0, clazz.size());
    }

    public void add(T element) {
        checkSize("add");
        objArray[this.index++] = element;
    }

    public void add(int index, T element) {
        objArray[index] = element;
    }

    public void push(int index, T element) {
        checkSize("add");
        biasForward(index);
        objArray[index] = element;
    }

    public T get(int index) {
        return (T) objArray[index];
    }

    public void remove(int index) {
        if (index + 1 > this.index) {
            throw new ArrayIndexOutOfBoundsException("Element with this index didn't exists");
        }
        biasBack(index);
        checkSize("remove");
    }

    public int size() {
        return (this.index);
    }

    public void sort(Comparator<T> parameter) {
        T[] bufArray = (T[]) new Object[this.index];
        System.arraycopy(objArray.clone(), 0, bufArray, 0, this.index);
        Arrays.sort(bufArray, parameter);
        System.arraycopy(bufArray, 0, objArray, 0, this.index);
    }

    @Override
    public String toString() {
        T[] bufArray = (T[]) new Object[this.index];
        System.arraycopy(objArray, 0, bufArray, 0, this.index);
        return Arrays.toString(bufArray);
    }

    private void biasBack(int index) {
        if (this.index - index >= 0) {
            System.arraycopy(objArray.clone(), index + 1, objArray, index, this.index - index);
        }
        this.index--;
    }

    private void biasForward(int index) {
        this.index++;
        if (this.index - index >= 0) {
            System.arraycopy(objArray.clone(), index, objArray, index + 1, this.index - index);
        }
    }

    private void checkSize(String command) {
        final int CHANGE_SIZE = 2;
        switch (command) {
            case "add":
                if (this.index == objArray.length - 1) {
                    resize(objArray.length * CHANGE_SIZE);
                }
                break;
            case "remove":
                if (objArray.length > INIT_SIZE && this.index < objArray.length / CHANGE_SIZE) {
                    resize(objArray.length / 2);
                }
                break;
        }
    }

    private void resize(int newSize) {
        objArray = Arrays.copyOf(objArray, newSize);
    }
}
