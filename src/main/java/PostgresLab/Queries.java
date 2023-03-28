package PostgresLab;

import PostgresLab.DAO.PersonDAO;
import PostgresLab.DAO.PetDAO;
import PostgresLab.DTO.FamilyLeaf;
import PostgresLab.DTO.PersonWithPetName;
import PostgresLab.DTO.PersonWithPetsCount;
import PostgresLab.Model.Person;
import PostgresLab.Sevice.castService;
import PostgresLab.Sevice.printService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Queries {
    public void insertPersons()
    {
        PersonDAO personDAO = new PersonDAO();
        PetDAO petDAO = new PetDAO();
        //personDAO.insertByDefault(1000);
        List<FamilyLeaf> result = castService.castToLeaf(personDAO.getFamilyTreeById(9001));
        List<PersonWithPetsCount> personWithPetsCounts = castService.castToPersonWithPetsCount(personDAO.getPersonsWithPetsCountAboveParam(5));
        List<PersonWithPetName> personWithPetNames = castService.castToPersonWithPetName(personDAO.getTenPersonsOldestWithTheirAlivesPets());
        List<Person> allPersons = personDAO.getPersons();



        printService.printFamilyTree(result);
        printService.printPersonWithPetsCount(personWithPetsCounts);
        printService.printPersonWithPetName(personWithPetNames);
        printService.printPersons(allPersons);
        printService.printPets(petDAO.getAllPets());


    }
}
