package PostgresLab.DAO;

import PostgresLab.DTO.PersonWithPetsCount;
import PostgresLab.HibernateUtil;
import PostgresLab.Model.Person;
import PostgresLab.Sevice.randomService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class PersonDAO {
    public List<Object[]> getFamilyTreeById(int id)
    {
        try (Session session = HibernateUtil.configureSession())
        {
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("WITH RECURSIVE r AS (\n" +
                    "   SELECT id, parent_id, firstname,lastname, 1 AS level\n" +
                    "   FROM person\n" +
                    "   WHERE id =:id \n" +
                    "\n" +
                    "   UNION ALL\n" +
                    "\n" +
                    "   SELECT person.id, person.parent_id, person.firstname,person.lastname, r.level + 1 AS level\n" +
                    "   FROM person\n" +
                    "      JOIN r\n" +
                    "          ON person.parent_id = r.id\n" +
                    ")\n" +
                    "\n" +
                    "SELECT * FROM r;");
            query.setParameter("id", id);
            List<Object[]> list = query.getResultList();
            //query.executeUpdate();
            tx.commit();
            session.close();
            return  list;
        }
    }
    public List<Object[]> getPersonsWithPetsCountAboveParam(int border)
    {
        {
            try (Session session = HibernateUtil.configureSession())
            {
                Transaction tx = session.beginTransaction();
                Query query = session.createSQLQuery("select firstname,lastname,pets_c.ct from \n" +
                        "(select sel.person_id, sel.ct from \n" +
                        "\t(select person_id,date_of_death,count(id) as ct from \n" +
                        " \t\tpet group by person_id, date_of_death having date_of_death is null order by ct DESC) as sel\n" +
                        "\twhere sel.ct > :count) as pets_c\n" +
                        "\tinner join public.person as p \n" +
                        "on p.id = pets_c.person_id");
                query.setParameter("count", border);
                List<Object[]> list = query.getResultList();
                //query.executeUpdate();
                tx.commit();
                session.close();
                return  list;
            }
        }
    }
    public List<Object[]> getTenPersonsOldestWithTheirAlivesPets()
    {
        {
            try (Session session = HibernateUtil.configureSession())
            {
                Transaction tx = session.beginTransaction();
                Query query = session.createSQLQuery("select firstname,lastname, pet_name \n" +
                        "\tFROM ( SELECT firstname,lastname,id,date_of_birth \n" +
                        "\t  FROM (select id, date_of_birth, lastname, firstname, dense_rank() OVER (PARTITION BY lastname order by date_of_birth) as pos \n" +
                        "\t\t\tfrom public.person) as ss where pos = 1 order by date_of_birth limit 10 ) as req\n" +
                        "\tinner join public.pet as pet \n" +
                        "on req.id=pet.person_id ");
                List<Object[]> list = query.getResultList();
                tx.commit();
                session.close();
                return  list;
            }
        }
    }
    public List<Person> getPersons ()
    {
        try (Session session = HibernateUtil.configureSession())
        {
            session.beginTransaction();
            Query query = session.createQuery("from Person");
            List<Person> list = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return list;
        }
    }


    public void insetPerson(Person person)
    {
        try (Session session = HibernateUtil.configureSession())
        {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
            session.close();
        }
    }
    public void insertByDefault(int count)
    {
        ArrayList<Person> persons = randomService.randomPersons(count);
        try (Session session = HibernateUtil.configureSession())
        {
            Transaction tx = session.beginTransaction();
            for (Person p : persons)
            {
                session.save(p);
            }
            tx.commit();
            session.close();
            // updateParents();
        }
    }
    public void updateParents()
    {
        try (Session session = HibernateUtil.configureSession())
        {
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE  public.person \n" +
                    "SET     parent_id = b.id\n" +
                    "FROM     (SELECT lastname,id,date_of_birth\n" +
                    "\tFROM (select id, date_of_birth, lastname, dense_rank() OVER (PARTITION BY lastname order by date_of_birth) as pos from public.person) as ss where pos = 1) as b\n" +
                    "            where public.person.lastname = b.lastname and public.person.id <> b.id");
            query.executeUpdate();
            tx.commit();
            session.close();
        }
    }
    public void insertForReqursion()
    {
        try (Session session = HibernateUtil.configureSession())
        {
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("insert into person (id,parent_id,firstname,lastname,date_of_birth,date_of_death) values\n" +
                    "(9001,9000,'Мыш','Коллинз','22/02/1700','22/01/1750'),\n" +
                    "(9002,9001,'Дин','Коллинз','23/03/1730','23/01/1780'),\n" +
                    "(9003,9001,'Сэм','Коллинз','27/04/1720','20/02/1780'),\n" +
                    "(9004,9002,'Егор','Коллинз','28/05/1750','21/11/1800'),\n" +
                    "(9005,9003,'Михаил','Коллинз','11/11/1770','22/11/1820'),\n" +
                    "(9006,9003,'Степан','Коллинз','12/12/1780','23/11/1820'),\n" +
                    "(9007,9005,'Мыш','Коллинз','13/11/1810','15/05/1835'),\n" +
                    "(9008,9005,'Альберт','Коллинз','20/05/1810','15/06/1840'),\n" +
                    "(9009,9008,'Николай','Коллинз','25/04/1830','16/02/1890'),\n" +
                    "(9010,9007,'Илья','Коллинз','13/03/1870','18/04/1970'),\n" +
                    "(9011,9010,'Евгений','Коллинз','14/02/1900','19/03/1960'),\n" +
                    "(9012,9011,'Михаил','Коллинз','16/03/1920','20/02/1942'),\n" +
                    "(9013,9012,'Егор','Коллинз','17/05/1940','24/02/1990'),\n" +
                    "(9014,9013,'Мыш','Коллинз','19/06/1957','12/05/2000'),\n" +
                    "(9015,9014,'Степан','Коллинз','11/07/1977',null),\n" +
                    "(9016,9015,'Михаил','Коллинз','12/02/1997',null),\n" +
                    "(9017,9016,'Евгений','Коллинз','13/01/2018',null)");
            query.executeUpdate();
            tx.commit();
            session.close();
        }
    }









}
