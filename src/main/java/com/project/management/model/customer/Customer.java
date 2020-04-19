package com.project.management.model.customer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Table(name = "customers")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public @Data class Customer {

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany() //fetch = FetchType.EAGER
    @JoinTable(
            name = "cust_proj",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Customer> customers;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
