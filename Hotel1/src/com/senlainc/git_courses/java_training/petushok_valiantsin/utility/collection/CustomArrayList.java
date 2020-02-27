package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    private int index = 0;
    private T[] objArray = (T[]) new Object[INIT_SIZE];

    public CustomArrayList() {

    }

    public CustomArrayList(List<T> list) {
        addAll(list);
    }

    @Override
    public boolean add(T element) {
        checkSize("add");
        objArray[this.index++] = element;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() > 0) {
            System.arraycopy(c.toArray(), 0, objArray, 0, c.size());
            this.index = c.size();
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(int index, T element) {
        objArray[index] = element;
    }

    @Override
    public T get(int index) {
        return objArray[index];
    }

    @Override
    public T set(int index, T element) {
        checkSize("add");
        biasForward(index);
        objArray[index] = element;
        return null;
    }

    @Override
    public T remove(int index) {
        if (index + 1 > this.index) {
            throw new ArrayIndexOutOfBoundsException("Element with this index didn't exists");
        }
        biasBack(index);
        checkSize("remove");
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int size() {
        return (this.index);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        T[] bufArray = (T[]) new Object[this.index];
        System.arraycopy(objArray.clone(), 0, bufArray, 0, this.index);
        Arrays.sort(bufArray, comparator);
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
            default:
                break;
        }
    }

    private void resize(int newSize) {
        objArray = Arrays.copyOf(objArray, newSize);
    }
}
