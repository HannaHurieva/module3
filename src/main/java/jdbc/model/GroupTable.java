package jdbc.model;

public class GroupTable extends BaseTable {
    private String title;
    private String specialty;
    private String department;
    private int numberOfStudents;

    public GroupTable() {
    }

    public GroupTable(long id, String title, String specialty, String department, int numberOfStudents) {
        super(id);
        this.title = title;
        this.specialty = specialty;
        this.department = department;
        this.numberOfStudents = numberOfStudents;
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
}
