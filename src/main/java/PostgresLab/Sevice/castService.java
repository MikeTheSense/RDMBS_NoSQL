package PostgresLab.Sevice;

import PostgresLab.DTO.FamilyLeaf;
import PostgresLab.DTO.PersonWithPetName;
import PostgresLab.DTO.PersonWithPetsCount;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class castService {
    public static List<FamilyLeaf> castToLeaf(List<Object[]> list)
    {
        if (list==null || list.isEmpty())
            return Collections.EMPTY_LIST;
        var familyTree = new ArrayList<FamilyLeaf>();
        for (Object[] leaf : list)
        {
            familyTree.add(new FamilyLeaf((int)leaf[0],(int)leaf[1],leaf[2].toString(),leaf[3].toString(),(int)leaf[4]));
        }
        return familyTree;
    }
    public static List<PersonWithPetsCount> castToPersonWithPetsCount(List<Object[]> list)
    {
        if (list==null || list.isEmpty())
            return Collections.EMPTY_LIST;
        var personsWithPetsCounts = new ArrayList<PersonWithPetsCount>();
        for (Object[] personWithPetsCount : list)
        {
            BigInteger bigInteger = (BigInteger)personWithPetsCount[2];
            personsWithPetsCounts.add(new PersonWithPetsCount(
                    personWithPetsCount[0].toString(),
                    personWithPetsCount[1].toString(),
                    bigInteger.intValue()
                    )
            );
        }
        return personsWithPetsCounts;
    }
    public static List<PersonWithPetName> castToPersonWithPetName(List<Object[]> list)
    {
        if (list==null || list.isEmpty())
            return Collections.EMPTY_LIST;
        var personsWithPetName = new ArrayList<PersonWithPetName>();
        for (Object[] personWithPetName : list)
        {
            personsWithPetName.add(new PersonWithPetName(
                    personWithPetName[0].toString(),
                    personWithPetName[1].toString(),
                    personWithPetName[2].toString()
                    )
            );
        }
        return personsWithPetName;
    }
}
