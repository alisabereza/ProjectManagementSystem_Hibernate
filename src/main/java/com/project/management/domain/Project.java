package com.project.management.domain;

import java.time.LocalDate;

public class Project {
    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
    private int cost;
    private Company company;

    public Project(String name, LocalDate start_date, LocalDate end_date, int cost, String companyName) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.cost = cost;
        this.company = new Company (companyName, LocalDate.parse("1900-01-01"));
    }

    public Project (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public int getCost() {
        return cost;
    }


    public Company getCompany() {
        return company;
    }

    public String getCompanyName() {
        return company.getName();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Project(String name, LocalDate start_date, int cost, String companyName) {
        this.name = name;
        this.start_date = start_date;
        this.company = new Company (companyName, LocalDate.parse("1900-01-01"));
        this.cost = cost;
    }
}
