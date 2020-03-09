INSERT INTO public.customers ( customer_name, customer_phone)
VALUES ('Zavod Mayak', '044-459-34-43');
INSERT INTO public.customers ( customer_name, customer_phone)
VALUES ('Univermag Ukraina', '044-459-12-32');
INSERT INTO public.customers ( customer_name, customer_phone)
VALUES ('Sushia', '044-450-09-33');
INSERT INTO public.customers ( customer_name, customer_phone)
VALUES ('InterSport', '044-233-15-32');

INSERT INTO public.companies (company_name, start_date)
VALUES ('EMAM', '01-01-2019');
INSERT INTO public.companies (company_name, start_date)
VALUES ('IT_Master', '01-01-2012');
INSERT INTO public.companies (company_name, start_date)
VALUES ('Glogic', '01-01-2013');
INSERT INTO public.companies (company_name, start_date)
VALUES ('Future', '01-01-2014');

INSERT INTO public.skills ( language, level)
VALUES ('Java', 1);
INSERT INTO public.skills ( language, level)
VALUES ('Java', 2);
INSERT INTO public.skills ( language, level)
VALUES ('Java', 3);
INSERT INTO public.skills ( language, level)
VALUES ('Phyton', 1);
INSERT INTO public.skills ( language, level)
VALUES ('Phyton', 2);
INSERT INTO public.skills ( language, level)
VALUES ('Phyton', 3);
INSERT INTO public.skills ( language, level)
VALUES ('JS', 1);
INSERT INTO public.skills ( language, level)
VALUES ('JS', 2);
INSERT INTO public.skills ( language, level)
VALUES ('JS', 3);
INSERT INTO public.skills ( language, level)
VALUES ('C++', 1);
INSERT INTO public.skills ( language, level)
VALUES ('C++', 2);
INSERT INTO public.skills ( language, level)
VALUES ('C++', 3);

INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Dmitry Potehov', 25, 'man', 2, 1000);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Nadia Potehova', 25, 'woman', 1, 2000);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Mario Nicca', 28, 'man', 3, 1500);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Maria Petrova', 32, 'woman', 4, 2000);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Ivan Bereza', 24, 'man', 1, 1500);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Orest Vyshnya', 30, 'man', 2, 2000);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Olga Soroka', 29, 'woman', 3, 1500);
INSERT INTO public.developers (developer_name, developer_age, developer_gender, company_id, salary)
VALUES ('Igor Vedmid', 35, 'man', 4, 3000);

INSERT INTO public.projects (project_name, start_date , company_id, cost)
VALUES ('Smart City', '01-01-2019', 1, 1000000);
INSERT INTO public.projects (project_name, start_date , company_id, cost)
VALUES ('Smart Country', '01-01-2015', 2, 2500000);
INSERT INTO public.projects (project_name, start_date , company_id, cost)
VALUES ('Digital Milk', '01-04-2014', 3, 1000000);
INSERT INTO public.projects (project_name, start_date , company_id, cost)
VALUES ('Space Visualization', '01-05-2010' , 4, 2500000);
INSERT INTO public.projects (project_name, start_date , company_id, cost)
VALUES ('Transport to the Mars', '01-12-2011', 1, 1000000);
INSERT INTO public.projects (project_name, start_date, company_id, cost)
VALUES ('Virtual Doctor', '01-01-2015', 2, 2500000);
INSERT INTO public.projects (project_name, start_date, company_id, cost)
VALUES ('Virtual Government', '01-01-2000', 3, 1500000);
INSERT INTO public.projects (project_name, start_date,  company_id, cost)
VALUES ('Smart Police', '01-01-2020',  4, 1500000);
INSERT INTO public.projects (project_name, start_date,  company_id, cost)
VALUES ('Virtual Pets', '01-03-2005',  1, 1500000);
INSERT INTO public.projects (project_name, start_date,  company_id, cost)
VALUES ('Air Dog', '01-01-2000',  2, 1500000);
INSERT INTO public.projects (project_name, start_date,  company_id, cost)
VALUES ('Future Games', '01-12-2019', 3, 1500000);
INSERT INTO public.projects (project_name, start_date,  company_id, cost)
VALUES ('AntiDrug', '01-01-1999',  4, 1500000);
INSERT INTO public.projects (project_name, start_date, end_date, company_id, cost)
VALUES ('Sport in Smartphone', '01-01-2001', '20-12-2019', 2, 3000000);

INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (1,1);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (1,2);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (1,3);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (1,4);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (2,5);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (2,6);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (2,7);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (2,8);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (3,9);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (3,10);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (3,11);
INSERT INTO public.cust_proj(customer_id, project_id)
VALUES (3,12);



INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (2,1);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (2,5);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (5,9);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (1,2);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (1,6);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (6,10);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (2,8);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (3,9);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (3,10);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (3,11);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (3,3);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (3,7);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (7,11);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (8,4);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (8,8);
INSERT INTO public.dev_proj(developer_id, project_id)
VALUES (4,12);

INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (1,1);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (2,2);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (3,3);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (4,4);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (5,5);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (6,6);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (7,7);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (8,8);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (1,9);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (2,10);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (3,11);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (4,12);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (5,1);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (6,2);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (7,3);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (8,4);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (1,5);
INSERT INTO public.dev_skills(developer_id, skill_id)
VALUES (2,6);


