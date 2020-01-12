package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.MenuIndex;

public class MenuItem {
    private final int id = new MenuIndex().getIndex();
    private final String title;
    private IAction action;
    private final Menu nextMenu;

    public MenuItem(String title, Menu nextMenu) {
        this.title = title;
        this.nextMenu = nextMenu;
    }

    public int getId() {
        return this.id;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public void doAction() {
        action.execute();
    }

    @Override
    public String toString() {
        return id + ")" + title;
    }
}
