package com.project.management.model.company;

import com.project.management.model.developer.Developer;
import com.project.management.model.project.Project;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "companies")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public @Data class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Developer> developers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Project> projects;


    public Company(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", start_date=" + startDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return name.equals(company.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
