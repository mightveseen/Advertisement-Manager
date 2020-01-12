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
        for (int i = 0; i < menuItemList.size(); i++) {
            if (index == menuItemList.get(i).getId()) {
                return menuItemList.get(i);
            }
        }
        return null;
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
        return name + "\n" + menu;
    }
}
