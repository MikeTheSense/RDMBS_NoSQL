package PostgresLab.DTO;

import java.io.Serializable;

public class FamilyLeaf implements Serializable {
    private int id;
    private int parent_id;
    private String firstname;
    private String lastname;
    private int level;

    public FamilyLeaf(int id, int parent_id, String firstname, String lastname, int level) {
        this.id = id;
        this.parent_id = parent_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getLevel() {
        return level;
    }
}
