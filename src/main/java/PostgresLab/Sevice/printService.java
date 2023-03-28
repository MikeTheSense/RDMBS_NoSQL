package PostgresLab.Sevice;

import PostgresLab.DTO.FamilyLeaf;
import PostgresLab.DTO.PersonWithPetName;
import PostgresLab.DTO.PersonWithPetsCount;
import PostgresLab.Model.Person;
import PostgresLab.Model.Pet;

import java.util.List;

public class printService {
    public static void printFamilyTree(List<FamilyLeaf> familyLeafList)
    {
        int i = 1;
        for (FamilyLeaf familyLeaf: familyLeafList)
        {
            String string = String.format("%s ID: %s ParentID: %s FisrtName: %s LastName: %s Level: %s;",
                    i,
                    familyLeaf.getId(),
                    familyLeaf.getParent_id(),
                    familyLeaf.getFirstname(),
                    familyLeaf.getLastname(),
                    familyLeaf.getLevel()
            );
            System.out.println(string);
            i++;
        }
    }
    public static void printPersonWithPetsCount(List<PersonWithPetsCount> personsWithPetsCounts)
    {
        int i = 1;
        for (PersonWithPetsCount personWithPetsCounts: personsWithPetsCounts)
        {
            String string = String.format("%s FisrtName: %s LastName: %s PetsCount: %s;",
                    i,
                    personWithPetsCounts.getFirstName(),
                    personWithPetsCounts.getSecondName(),
                    personWithPetsCounts.getPetsCount()
            );
            System.out.println(string);
            i++;
        }
    }
    public static void printPersonWithPetName(List<PersonWithPetName> personsWithPetName)
    {
        int i = 1;
        for (PersonWithPetName personWithPetName: personsWithPetName)
        {
            String string = String.format("%s FisrtName: %s LastName: %s PetName: %s;",
                    i,
                    personWithPetName.getFirstName(),
                    personWithPetName.getLastName(),
                    personWithPetName.getPetName()
            );
            System.out.println(string);
            i++;
        }
    }
    public static void printPersons(List<Person> persons)
    {
        int i = 1;
        for (Person person: persons)
        {
            String string = String.format("%s ID: %s FirstName: %s LastName: %s DateOfBirth: %s DateOfDeath: %s;",
                    i,
                    person.getId(),
                    person.getFirstname(),
                    person.getLastname(),
                    person.getDateOfBirth(),
                    person.getDateOfDeath()
            );
            System.out.println(string);
            i++;
        }
    }
    public static void printPets(List<Pet> pets)
    {
        int i = 1;
        for (Pet pet: pets)
        {
            String string = String.format("%s ID: %s Name: %s DateOfBirth: %s DateOfDeath: %s %s;",
                    i,
                    pet.getId(),
                    pet.getPetname(),
                    pet.getDateOfBirth(),
                    pet.getDateOfDeath(),
                    pet.getPerson().getId()
            );
            System.out.println(string);
            i++;
        }
    }


}
