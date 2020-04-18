package com.project.management.domain;

import java.util.Arrays;
import java.util.Optional;

public enum DeveloperGender {
    MAN("MAN"), WOMAN("WOMAN");

    private String gender;

    DeveloperGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static Optional<DeveloperGender> getCourseStatus(String status) {
        return Arrays.stream(DeveloperGender.values())
                .filter(enumValue -> enumValue.getGender().equals(status))
                .findAny();
    }
}
