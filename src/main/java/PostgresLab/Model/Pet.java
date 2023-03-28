package PostgresLab.Model;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "Pet")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id")
    private int id;


    @Column(name = "pet_name")
    @NotNull
    private String petname;

    @Column(name = "date_of_birth")
    @NotNull
    private Date dateOfBirth;


    @Column(name = "date_of_death")
    private Date dateOfDeath;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;


    public Pet(String petname, Date dateOfBirth, Date dateOfDeath, Person person) {
        this.petname = petname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.person = person;
    }
    public Pet(int id,String petname, Date dateOfBirth, Date dateOfDeath, Person person) {
        this.id = id;
        this.petname = petname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.person = person;
    }
    public Pet(String petname, Date dateOfBirth, Date dateOfDeath) {
        this.petname = petname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.person = person;
    }

    public Pet() {
    }

    public String getPetname() {
        return petname;
    }
    public int getId() {
        return id;
    }
    public void setPetname(String petname) {
        this.petname = petname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}