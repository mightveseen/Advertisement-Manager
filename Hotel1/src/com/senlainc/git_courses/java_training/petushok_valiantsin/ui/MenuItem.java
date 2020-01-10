package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

public class MenuItem {
//    private final int id;
    private final String title;
    private final IAction action;
    private final Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.action = action;
        this.title = title;
        this.nextMenu = nextMenu;
    }

    public String getTitle() {
        return this.title;
    }

    public IAction getAction() {
        return this.action;
    }

    public Menu getNextMenu() {
        return this.nextMenu;
    }
}
