package PostgresLab.DTO;

public class PersonWithPetName {
    private String firstName;
    private String lastName;
    private String petName;

    public PersonWithPetName(String firstName, String lastName, String petName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.petName = petName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPetName() {
        return petName;
    }
}
