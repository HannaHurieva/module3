package hibernate.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subject_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "hours")
    private int hours;

    @Column(name = "cost_per_hour")
    private int cost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_group",
            //foreign key for GroupSt in subject_group table
            joinColumns = @JoinColumn(name = "subjects_id"),
            //foreign key for other side - Subject in subject_group table
            inverseJoinColumns = @JoinColumn(name = "groups_st_specialty"))
    private Set<GroupSt> groups = new HashSet<>();

    public Subject() {
    }

    public Subject(String title, String type, int hours, int cost) {
        this.title = title;
        this.type = type;
        this.hours = hours;
        this.cost = cost;
    }

    public Set<GroupSt> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupSt> groups) {
        this.groups = groups;
    }

    public void addGroup(GroupSt group) {
        groups.add(group);
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + subject_id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", hours=" + hours +
                ", cost=" + cost +
                '}';
    }
}
