package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String name;
    private final List<MenuItem> menuItemList = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public MenuItem getItem(int index) {
        return menuItemList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new);
    }

    public void addItem(MenuItem menuItem) {
        menuItemList.add(menuItem);
    }

    @Override
    public String toString() {
        StringBuilder menu = new StringBuilder();
        for (MenuItem item : menuItemList) {
            menu.append(item).append("\n");
        }
        return menu.toString();
    }
}
