package com.project.management.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "project_name")
    private String name;
    @Column(name = "start_date")
    private LocalDate start_date;
    @Column(name = "end_date")
    private LocalDate end_date;
    @Column(name = "cost")
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne // connection to Company: many Projects one Company
    @JoinColumn(name = "company_id") // company_id is Company id
    private Company company;

    @ManyToMany() //fetch = FetchType.EAGER
    @JoinTable(
            name = "dev_proj",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> developers;

    @ManyToMany() //fetch = FetchType.EAGER
    @JoinTable(
            name = "cust_proj",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public Project() {
    }

    public Project(String name, LocalDate start_date, LocalDate end_date, int cost, Company company) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.cost = cost;
        this.company = company;
    }

    public Project(String name) {
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

    public Project(String name, LocalDate start_date, int cost, Company company) {
        this.name = name;
        this.start_date = start_date;
        this.company = company;
        this.cost = cost;
    }
}
