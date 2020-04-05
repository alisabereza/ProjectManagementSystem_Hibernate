package com.project.management.domain;

public enum DeveloperGender {
    man("man"), woman("woman");

    private String gender;

    DeveloperGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
