package com.senlainc.javacourses.petushokvaliantsin.enumeration;

public enum EnumRole {

    ROLE_ADMIN("ADMIN"), ROLE_MODERATOR("MODERATOR"), ROLE_COMMON("COMMON");

    private final String role;

    EnumRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
