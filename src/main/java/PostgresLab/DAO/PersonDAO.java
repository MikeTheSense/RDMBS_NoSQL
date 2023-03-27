package PostgresLab.DAO;

import PostgresLab.HibernateUtil;
import PostgresLab.Model.Person;
import PostgresLab.Sevice.randomService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;


public class PersonDAO {
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









}
