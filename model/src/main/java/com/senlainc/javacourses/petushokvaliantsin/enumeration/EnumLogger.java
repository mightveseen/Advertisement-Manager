package com.senlainc.javacourses.petushokvaliantsin.enumeration;

public enum EnumLogger {

    SUCCESSFUL_CREATE("Successful execution of create operation"),
    SUCCESSFUL_DELETE("Successful execution of delete operation"),
    SUCCESSFUL_UPDATE("Successful execution of update operation"),
    SUCCESSFUL_READ("Successful execution of read operation");

    private final String text;

    EnumLogger(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
