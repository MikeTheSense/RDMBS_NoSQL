package PostgresLab.DAO;

import PostgresLab.HibernateUtil;
import PostgresLab.Model.Person;
import PostgresLab.Model.Pet;
import PostgresLab.Sevice.randomService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    public List<Pet> getAllPets ()
    {
        {
            try (Session session = HibernateUtil.configureSession())
            {
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from Pet");
                List<Pet> list = query.getResultList();
                tx.commit();
                session.close();
                return  list;
            }
        }
    }


}
