package com.senlainc.javacourses.petushokvaliantsin.enumeration;

public enum EnumException {

    PERMISSION("You don't have a permission"),
    CHAT_EXIST("Chat with user already exist"),
    RATE_YOURSELF("You can't rate yourself"),
    RATE_EXIST("You already rated this user"),
    USER_WITH_FIELD_EXIST("User with %s [%s] already exist"),
    USER_WITH_FIELD_NOT_EXIST("User with %s [%s] not exist"),
    ENTITY_NOT_EXIST("%s with %s [%s] not exits"),
    ACTIVE_PAYMENT("You have: [%d] active payment's");

    private final String message;

    EnumException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
