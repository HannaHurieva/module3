package jdbc.model;

public class SubjectGroupTable extends BaseTable {
    private int subjectId;
    private int groupId;

    public SubjectGroupTable() {
    }

    public SubjectGroupTable(long id, int subjectId, int groupId) {
        super(id);
        this.subjectId = subjectId;
        this.groupId = groupId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
