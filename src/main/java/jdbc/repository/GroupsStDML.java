package jdbc.repository;

import java.sql.SQLException;

public class GroupsStDML extends TableBaseOperations implements CRUD {

    public GroupsStDML() throws SQLException {
        super("groups_st");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS " + tableName +
                        "(group_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
                        "title VARCHAR(255) NOT NULL, " +
                        "specialty VARCHAR(255), " +
                        "department VARCHAR(255), " +
                        "number_of_students INT UNSIGNED DEFAULT 0);",
                        "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {

    }

    public void insertValues(String title, String specialty, String department, int numberOfStudents) throws SQLException {
        super.executeSqlStatement("INSERT INTO " + tableName +
                        "(title, specialty, department, number_of_students) " +
                        "VALUES ('" + title + "', '" + specialty + "', '" + department + "', " + numberOfStudents + ");",
                        "Добавлены данные в таблицу " + tableName);
    }

    public void getSortingByGroupsTitlesWithNumberOfStudents() throws SQLException {
        int[] indexColumns = {1, 2};
        super.executeQuerySql("SELECT title, number_of_students FROM groups_st ORDER BY title;",
                            "ЗАПРОС: Вывести список названий групп с указанием количества студентов." +
                            "\n        Отсортировать по названию группы.",
                            indexColumns);
    }

    public void getSubjectsHoursListBySpeciality() throws SQLException {
        int[] indexColumns = {1, 2, 3, 4};
        super.executeQuerySql("SELECT DISTINCT groups_st.specialty, subjects.title, subjects.`type`, subjects.hours " +
                        "FROM groups_st " +
                        "JOIN subject_group ON groups_st.group_id = subject_group.groups_st_specialty " +
                        "JOIN subjects ON subject_group.subjects_id = subjects.subject_id;",
                        "ЗАПРОС: Вывести список предметов для каждой специальности" +
                        "\n        с указанием количества отведенных на них часов.",
                        indexColumns);
    }
}
