package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="teachers")
public class Teachers {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="second_name")
    private String secondName;

    @Column(name="tel")
    private String tel;

    @Column(name="exp")
    private int experience;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject_id", fetch = FetchType.LAZY)
    private List<Subjects> subjects;

    public Teachers() {
    }

    public Teachers(String lastName, String firstName, String secondName, String tel, int experience) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.tel = tel;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", second_name='" + secondName + '\'' +
                ", tel='" + tel + '\'' +
                ", experience=" + experience +
                '}';
    }
}
