package com.project.management.model.project;

import com.project.management.model.company.Company;
import com.project.management.model.customer.Customer;
import com.project.management.model.developer.Developer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Table(name = "projects")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public @Data class Project {

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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany()
    @JoinTable(
            name = "dev_proj",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> developers;

    @ManyToMany()
    @JoinTable(
            name = "cust_proj",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public Project(String name, LocalDate start_date, LocalDate end_date, int cost, Company company) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.cost = cost;
        this.company = company;
    }

    public Project(String name, LocalDate start_date, int cost, Company company) {
        this.name = name;
        this.start_date = start_date;
        this.company = company;
        this.cost = cost;
    }
}
