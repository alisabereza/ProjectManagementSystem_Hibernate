package com.project.management.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Table(name ="developers")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="developer_name")
    private String name;

    @Column(name="developer_age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name="developer_gender")
    private DeveloperGender gender;

    @Column(name="salary")
    private int salary;

    @ManyToOne // connection to Company: many Projects one Company
    @JoinColumn(name = "company_id") // company_id is Company id
    private Company company;

    @ManyToMany //fetch = FetchType.EAGER
     @JoinTable(
            name = "dev_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")

    )
    private List<Skill> skills;

    @ManyToMany //fetch = FetchType.EAGER
    @JoinTable(
            name = "dev_proj",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")

    )
    private List<Project> projects;


    public Developer () {}

    public Developer(String name, int age, DeveloperGender gender, int salary, Company company) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Developer(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender.getGender();
    }

    public void setGender(DeveloperGender gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                ( (company==null)?", null": ", company =" + company.getName()) +
                '}';
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
