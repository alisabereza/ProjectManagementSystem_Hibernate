package com.project.management.domain;

public enum DeveloperGender {
    MAN("man"), WOMAN("woman");

    private String gender;
    DeveloperGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
}
