package com.senlainc.javacourses.petushokvaliantsin.starter;


public class StarterBean {

    private final String firstProperty;
    private final String secondProperty;

    public StarterBean(String firstProperty, String secondProperty) {
        this.firstProperty = firstProperty;
        this.secondProperty = secondProperty;
    }

    @Override
    public String toString() {
        return "StarterBean{" +
                "firstProperty='" + firstProperty + '\'' +
                ", secondProperty='" + secondProperty + '\'' +
                '}';
    }
}
