package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import java.util.LinkedList;
import java.util.List;

public class Menu {
    private String name;
    private List<MenuItem> menuItemList = new LinkedList<>();

    public Menu(String name) {
        this.name = name;
    }

    public MenuItem getItem(int index) {
        return this.menuItemList.get(index);
    }

    public void addItem(MenuItem menuItem) {
        menuItemList.add(menuItem);
    }

    public List<MenuItem> getItemList() {
        return this.menuItemList;
    }
}
