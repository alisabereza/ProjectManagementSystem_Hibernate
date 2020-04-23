package com.project.management.model.developer;

import com.project.management.model.project.Project;
import com.project.management.model.skill.Skill;
import com.project.management.model.company.Company;
import com.project.management.utils.EnumValidator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Table(name ="developers")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public @Data class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="developer_name")
    private String name;

    @Column(name="developer_age")
    private int age;

    public Company getCompany() {
        return company;
    }

    @EnumValidator(regexp = "MAN|WOMAN")
    @Enumerated(EnumType.STRING)
    @Column(name="developer_gender")
    private DeveloperGender gender;

    @Column(name="salary")
    private int salary;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
     @JoinTable(
            name = "dev_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")

    )
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "dev_proj",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")

    )
    private List<Project> projects;

    public Developer(String name, int age, DeveloperGender gender, int salary, Company company) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.company = company;
    }


    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                ( (company==null)?", null": ", company =" + company.getName()) +
                '}';
    }

}
