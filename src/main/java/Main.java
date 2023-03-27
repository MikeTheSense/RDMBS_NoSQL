import PostgresLab.HibernateUtil;
import PostgresLab.Queries;


public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        //var session = HibernateUtil.configureSession();
        Queries qu = new Queries();
        qu.insertPersons();



    }
}
