SELECT p.id, p.date_of_birth, p.date_of_death, p.firstname, p.lastname, p.parent_id, pet.id
FROM public.person as p
         inner join public.pet pet on p.id = person_id




--------
--10 oldest people with their alive pets
---------

select firstname,lastname, pet_name
FROM ( SELECT firstname,lastname,id,date_of_birth
       FROM (select id, date_of_birth, lastname, firstname, dense_rank() OVER (PARTITION BY lastname order by date_of_birth) as pos
             from public.person) as ss where pos = 1 order by date_of_birth limit 10 ) as req
         inner join public.pet as pet
                    on req.id=pet.person_id


select * from pet
--------
--Persons with pets count above 5
---------
select firstname,lastname,pets_c.ct from
    (select sel.person_id, sel.ct from
        (select person_id,date_of_death,count(id) as ct from
            pet group by person_id, date_of_death having date_of_death is null order by ct DESC) as sel
     where sel.ct > 5) as pets_c
        inner join public.person as p
                   on p.id = pets_c.person_id


--------
--Family tree
---------


    insert into person (id,parent_id,firstname,lastname,date_of_birth,date_of_death) values
    (9001,9000,'Мыш','Коллинз','22/02/1700','22/01/1750'),
    (9002,9001,'Дин','Коллинз','23/03/1730','23/01/1780'),
    (9003,9001,'Сэм','Коллинз','27/04/1720','20/02/1780'),
    (9004,9002,'Егор','Коллинз','28/05/1750','21/11/1800'),
    (9005,9003,'Михаил','Коллинз','11/11/1770','22/11/1820'),
    (9006,9003,'Степан','Коллинз','12/12/1780','23/11/1820'),
    (9007,9005,'Мыш','Коллинз','13/11/1810','15/05/1835'),
    (9008,9005,'Альберт','Коллинз','20/05/1810','15/06/1840'),
    (9009,9008,'Николай','Коллинз','25/04/1830','16/02/1890'),
    (9010,9007,'Илья','Коллинз','13/03/1870','18/04/1970'),
    (9011,9010,'Евгений','Коллинз','14/02/1900','19/03/1960'),
    (9012,9011,'Михаил','Коллинз','16/03/1920','20/02/1942'),
    (9013,9012,'Егор','Коллинз','17/05/1940','24/02/1990'),
    (9014,9013,'Мыш','Коллинз','19/06/1957','12/05/2000'),
    (9015,9014,'Степан','Коллинз','11/07/1977',null),
    (9016,9015,'Михаил','Коллинз','12/02/1997',null),
    (9017,9016,'Евгений','Коллинз','13/01/2018',null)


update person
set parent_id = 9000
where id = 9001


    WITH RECURSIVE r AS (
   SELECT id, parent_id, firstname,lastname, 1 AS level
   FROM person
   WHERE id = 9001

   UNION ALL

   SELECT person.id, person.parent_id, person.firstname,person.lastname, r.level + 1 AS level
   FROM person
      JOIN r
          ON person.parent_id = r.id
)

SELECT * FROM r;



