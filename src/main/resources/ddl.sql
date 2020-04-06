create database project_management with owner postgres;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table companies
(
    id SERIAL,
    company_name VARCHAR(30) NOT NULL,
    start_date date NOT NULL,
    PRIMARY KEY (id)
);


create table developers
(
    id SERIAL,
    developer_name VARCHAR(30) NOT NULL,
    developer_age INT,
    developer_gender VARCHAR(10),
    salary INT NOT NULL,
    company_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

create table skills
(
    id SERIAL,
    language VARCHAR(30) NOT NULL,
    level INT,
	PRIMARY KEY (id)
);

create table projects
(
    id SERIAL,
    project_name VARCHAR(30) NOT NULL,
    start_date date NOT NULL,
    end_date date,
    cost int NOT NULL,
    company_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id)  REFERENCES companies (id)
);


create table customers
(
    id SERIAL,
    customer_name VARCHAR(30) NOT NULL,
    customer_phone VARCHAR(30),
    PRIMARY KEY (id)
);


create table dev_skills
(
developer_id INT,
skill_id INT,
PRIMARY KEY (developer_id,skill_id),
    FOREIGN KEY (developer_id) REFERENCES developers (id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
);

create table dev_proj
(
    developer_id INT,
    project_id INT,
    PRIMARY KEY (developer_id,project_id),
    FOREIGN KEY (developer_id) REFERENCES developers (id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);


create table cust_proj
(
    customer_id INT,
    project_id INT,
    PRIMARY KEY (customer_id,project_id),
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);



