package PostgresLab.Model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "Person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    @NotNull
    private String firstname;
    @Column(name = "lastname")
    @NotNull
    private String lastname;
    @Column(name = "date_of_birth")
    @NotNull
    private Date dateOfBirth;


    @OneToOne
    @JoinColumn(name="parent_id", referencedColumnName = "id")
    private Person father;

    @Column(name = "date_of_death")
    private Date dateOfDeath;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "person")
    private List<Pet> pets = new LinkedList<Pet>();


    public Person(String firstname, String lastname, Date dateOfBirth, Person father, Date dateOfDeath, List<Pet> pets) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.father = father;
        this.dateOfDeath = dateOfDeath;
        this.pets = pets;
    }
    public Person(int id, String firstname, String lastname, Date dateOfBirth, Person father, Date dateOfDeath, List<Pet> pets) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.father = father;
        this.dateOfDeath = dateOfDeath;
        this.pets = pets;
    }

    public Person() {

    }

    public Person(int id, String firstname, String lastname, Date dateOfBirth, Person father, Date dateOfDeath) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.father = father;
        this.dateOfDeath = dateOfDeath;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
