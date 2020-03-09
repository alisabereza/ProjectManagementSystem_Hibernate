select sum(d.salary)
from developers d
         join dev_proj dp on d.id = dp.developer_id
where dp.project_id in (select id from projects where project_name = ?);

select developer_name
from developers d
         join dev_proj dp on d.id = dp.developer_id
where dp.project_id in (select id from projects where project_name = ?);

select developer_name
from developers d
         join dev_skills ds on d.id = ds.developer_id
where ds.skill_id in (select id from skills where language = ?);

-- This report returns developers with MAX level 2
select level.developer_name from
(select distinct devskill.developer_name, max(devskill.level) as level from
    (select d.id, d.developer_name, s.language, s.level, s.id
    from developers d
    join dev_skills ds on d.id = ds.developer_id
    join skills s on ds.skill_id = s.id) devskill
group by devskill.developer_name) level
where level.level = 2;

select proj.date, proj.proj_name, proj.count from
    (select  distinct p.project_name as proj_name, max(p.start_date) as date, count(dp.developer_id) as count from projects p join dev_proj dp on p.id = dp.project_id
    group by p.project_name
    order by p.project_name) proj

