package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.utility;

import java.util.Arrays;

public class MyList <T> {
    private int index = 0;
    private final int INIT_SIZE = 10;
    private final int CHANGE_SIZE = 2;
    private Object[] objArray = new Object[INIT_SIZE];

    public void add(T element) {
        if(this.index == objArray.length - 1) {
            resize(objArray.length * CHANGE_SIZE);
        }
        objArray[this.index++] = element;
    }
    public void add(int index, T element) {
        if(this.index == objArray.length - 1) {
            resize(objArray.length * CHANGE_SIZE);
        }
        objArray[index] = element;
    }
    public T get(int index) {
        return (T) objArray[index];
    }
    public void remove(int index) {
        biasBack(index);
        if(objArray.length > INIT_SIZE && this.index < objArray.length / CHANGE_SIZE) {
            resize(objArray.length / 2);
        }
    }
    private void biasBack(int index) {
        for(int i = index; i < this.index; i++) {
            objArray[i] = objArray[i+1];
        }
        objArray[this.index--] = null;
    }
    private void biasForward(int index) {
        this.index++;
        for(int i = this.index; i > index; i--) {
            objArray[i+1] = objArray[i];
        }
    }
    private void resize(int newSize) {
        objArray = Arrays.copyOf(objArray, newSize);
    }
}
