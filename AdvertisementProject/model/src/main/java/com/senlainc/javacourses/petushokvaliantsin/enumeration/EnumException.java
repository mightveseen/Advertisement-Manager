package com.senlainc.javacourses.petushokvaliantsin.enumeration;

public enum EnumException {
    PERMISSION_EXCEPTION("You don't have a permission"),
    CHAT_EXIST("Chat with this user already exist"),
    RATE_YOURSELF("You already rated this user"),
    RATE_EXIST("You already rated this user");

    private final String message;

    EnumException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
