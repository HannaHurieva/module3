package jdbc.model;

public class SubjectTable extends BaseTable {
    private String title;
    private String type;
    private int hours;
    private int cost;

    public SubjectTable() {
    }

    public SubjectTable(long id, String title, String type, int hours, int cost) {
        super(id);
        this.title = title;
        this.type = type;
        this.hours = hours;
        this.cost = cost;
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
}
