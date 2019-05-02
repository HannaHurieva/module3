package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups_st")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;

    @Column(name = "title")
    private String title;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "department")
    private String department;

    @Column(name = "number_of_students")
    private int numberOfStudents;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_group",
            //foreign key for Groups in subject_group table
            joinColumns = @JoinColumn(name = "groups_st_specialty"),
            //foreign key for other side - Subjects in subject_group table
            inverseJoinColumns = @JoinColumn(name = "subjects_id"))
    private Set<Subjects> subjects = new HashSet<>();

    public Groups() {
    }

    public Groups(String title, String specialty, String department, int numberOfStudents) {
        this.title = title;
        this.specialty = specialty;
        this.department = department;
        this.numberOfStudents = numberOfStudents;
    }

    public Set<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subjects> subjects) {
        this.subjects = subjects;
    }

    public void addSubject (Subjects subject){
        subjects.add(subject);
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "group_id=" + group_id +
                ", title='" + title + '\'' +
                ", specialty='" + specialty + '\'' +
                ", department='" + department + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                '}';
    }
}
