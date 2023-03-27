package PostgresLab.Sevice;



import PostgresLab.Model.Person;
import PostgresLab.Model.Pet;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class randomService {
    private static final String[] names =
            {       "Аркадий",
                    "Евгений",
                    "Михаил",
                    "Егор",
                    "Анатолий",
                    "Ахмед",
                    "Илья",
                    "Олег",
                    "Сергей",
                    "Ильнур",
                    "Закир",
                    "Владимир",
                    "Алексей",
                    "Николай",
                    "Владислав",
                    "Роман",
                    "Махмуд",
                    "Акакий",
                    "Мишал",
                    "Раджив",
                    "Томям",
                    "Карри",
                    "Мулсанш",};
    private static final String[] surnames =
            {       "Бочкарев",
                    "Грушин",
                    "Шорников",
                    "Ушмодин",
                    "Фадеев",
                    "Калеев",
                    "Рогаль",
                    "Ундрова",
                    "Квадрант",
                    "Сегхал",
                    "Картик",
                    "Асланбеков",
                    "Титаев",
                    "Бренев",
                    "Рязков",
                    "Извольский",
                    "Хованский",
                    "Яблоков",
                    "Персиков",
                    "Васильев",
                    "Соколов",
                    "Шматков",
                    "Петров",
                    "Сидоров",};
    public static Date randomDate(Date startInclusive,Date endExclusive)
    {
        long startMillis = startInclusive.getTime();
        long endMillis = Calendar.getInstance().getTimeInMillis();
        if (endExclusive!=null)
        endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }
    public static Date randomDate(Date startInclusive)
    {
        int chance = (int)(Math.random() * 100);
        if (chance>10)
            return null;
        long startMillis = startInclusive.getTime();
        long endMillis = Calendar.getInstance().getTimeInMillis();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }
    public static Date randomDate()
    {
        long endMillis = Calendar.getInstance().getTimeInMillis();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(19000000, endMillis);
        return new Date(randomMillisSinceEpoch);
    }
    public static String randomName()
    {
        return names[(int)(Math.random() * names.length)];
    }
    public static String randomSurname()
    {
        return surnames[(int)(Math.random() * surnames.length)];
    }
    public static int randomPerson(int range)
    {
        return (int)(Math.random() * range);
    }

    public static List<Pet> randomPetList(Person person, Date startInclusive, Date endExclusive)
    {
        int count = (int)(Math.random() * 10);
        List<Pet> pets = new ArrayList<>(count);
        for (int i=0; i<count; i++)
        {
            Date birth = randomDate(startInclusive,endExclusive);
            pets.add(new Pet(randomName(),birth,randomDate(birth),person));
        }
        return pets;
    }
    public static ArrayList<Person> randomPersons(int count)
    {
        ArrayList<Person> persons = new ArrayList<>(count);
        long year = 31536000000l;
        for (int i = 0; i<count;i++)
        {
            var dateOfBirth =  randomService.randomDate();
            var dateOfDeath = randomService.randomDate(dateOfBirth);
            Person person = new Person(randomService.randomName(), randomService.randomSurname(), dateOfBirth,null,dateOfDeath,null);
            person.setPets(randomService.randomPetList(person,dateOfBirth,dateOfDeath));
            persons.add(person);
        }
        return persons;
    }
}
