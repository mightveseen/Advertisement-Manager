package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui;

public class MenuItem {
    private final String title;
    private Menu nextMenu;
    private int id;
    private IAction action;

    public MenuItem(String title, IAction action) {
        this.title = title;
        this.action = action;
    }

    public MenuItem(String title, Menu nextMenu) {
        this.title = title;
        this.nextMenu = nextMenu;
    }

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int index) {
        this.id = index;
    }

    public void doAction() {
        if (action != null) {
            action.execute();
        }
    }

    public Menu getNextMenu() {
        return this.nextMenu;
    }

    @Override
    public String toString() {
        return id + ")" + title;
    }
}
