package PostgresLab;

import PostgresLab.DAO.PersonDAO;
import PostgresLab.DAO.PetDAO;

public class Queries {
    public void insertPersons()
    {
        PersonDAO personDAO = new PersonDAO();
        personDAO.insertByDefault(1000);

    }
}
