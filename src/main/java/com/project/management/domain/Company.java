package com.project.management.domain;

import java.time.LocalDate;


public class Company {
    private String name;
    private LocalDate start_date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company(String name, LocalDate start_date) {
        this.name = name;
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", start_date=" + start_date +
                '}';
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
}
