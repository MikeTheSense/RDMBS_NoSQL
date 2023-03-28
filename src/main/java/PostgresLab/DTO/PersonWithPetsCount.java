package PostgresLab.DTO;

public class PersonWithPetsCount {
    private String firstName;
    private String secondName;
    private int petsCount;

    public PersonWithPetsCount(String firstName, String secondName, int petsCount) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.petsCount = petsCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getPetsCount() {
        return petsCount;
    }
}
