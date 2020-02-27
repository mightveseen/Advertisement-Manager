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
        return menuItemList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    public List<MenuItem> getMenuItemList() {
        return this.menuItemList;
    }

    public void addItem(MenuItem menuItem) {
        menuItemList.add(menuItem);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        final StringBuilder menu = new StringBuilder();
        for (MenuItem item : menuItemList) {
            menu.append(item).append("\n");
        }
        menu.deleteCharAt(menu.length() - 1);
        return menu.toString();
    }
}
