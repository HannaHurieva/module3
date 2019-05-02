package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int subject_id;

    @Column(name="title")
    private String title;

    @Column(name="type")
    private String type;

    @Column(name="hours")
    private int hours;

    @Column(name="cost_per_hour")
    private int cost;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id", referencedColumnName = "id")
    private Teachers teacher;


    public Subjects() {
    }


    public Subjects(String title, String type, int hours, int cost) {
        this.title = title;
        this.type = type;
        this.hours = hours;
        this.cost = cost;
    }


    private Set<Groups> groups = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_group",
            //foreign key for Groups in subject_group table
            joinColumns = @JoinColumn(name = "subject_id"),
            //foreign key for other side - Subjects in subject_group table
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }


    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int id) {
        this.subject_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "id=" + subject_id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", hours=" + hours +
                ", cost=" + cost +
                '}';
    }
}
