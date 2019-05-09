package jdbc.repository;

import java.sql.SQLException;

public class TeachersDML extends TableBaseOperations implements CRUD {

    public TeachersDML() throws SQLException {
        super("teachers");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS " + tableName +
                "(id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
                "last_name VARCHAR(255) NOT NULL, " +
                "first_name VARCHAR(255), " +
                "second_name VARCHAR(255), " +
                "phone VARCHAR(13) NOT NULL, " +
                "experience INT UNSIGNED DEFAULT 0);", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {

    }

    public void insertValues(String lastName, String firstName, String secondName, String phone, int experience) throws SQLException {
        super.executeSqlStatement("INSERT INTO " + tableName +
                "(last_name, first_name, second_name, phone, experience) " +
                "VALUES ('" + lastName + "', '" + firstName + "', '" + secondName + "', '" + phone + "', " + experience + ");",
                "Добавлены данные в таблицу " + tableName);
    }

    public void getTeacherByExperience(int exp) throws SQLException {
        int[] indexColumns = {1,2,3};
        super.executeQuerySql("SELECT DISTINCT teachers.last_name, teachers.first_name, teachers.second_name " +
                "FROM teachers WHERE teachers.experience > " + exp + ";",
                "ЗАПРОС: Вывести ФИО всех преподавателей, стаж которых больше " + exp + " лет.",
                indexColumns);
    }


    public void getTeachersGroupsList() throws SQLException {
        int[] indexColumns = {1,2,3,4};
        super.executeQuerySql("SELECT DISTINCT teachers.first_name, teachers.last_name, teachers.second_name, groups_st.title " +
                        "FROM teachers " +
                        "JOIN subjects ON teachers.id = subjects.teacher_id " +
                        "JOIN subject_group ON subjects.subject_id = subject_group.subjects_id " +
                        "JOIN groups_st ON subject_group.groups_st_specialty = groups_st.group_id " +
                        "ORDER BY teachers.second_name ;",
                "ЗАПРОС: Вывести список преподавателей и номера групп, в которых они преподают.",
                indexColumns);
    }

    public void getCostHoursByTeacher(String lastName) throws SQLException {
        int[] indexColumns = {1,2,3,4};
        super.executeQuerySql("SELECT DISTINCT teachers.last_name, subjects.`type`, subjects.hours, subjects.cost_per_hour " +
                        "FROM teachers " +
                        "JOIN subjects ON teachers.id = subjects.teacher_id " +
                        "WHERE teachers.last_name = '" + lastName +"' ;",
                "ЗАПРОС: Посчитать нагрузку для преподавателя " + lastName + ".",
                indexColumns);
    }

    public void getTeachersByExperienceAndSubjects(int exp, String subj1,String subj2) throws SQLException {
        int[] indexColumns = {1,2,3};
        super.executeQuerySql("SELECT DISTINCT teachers.last_name, teachers.first_name, teachers.second_name " +
                        "FROM teachers " +
                        "JOIN subjects ON teachers.id = subjects.teacher_id WHERE teachers.experience > " + exp +
                        " AND (subjects.title = '" + subj1 + "' OR subjects.title = '" + subj2 + "');",
                "ЗАПРОС: Вывести список преподавателей, стаж которых больше " + exp + " лет " +
                        " и которые могут вести предметы '" + subj1 + "' или '" + subj2 +"'.",
                indexColumns);
    }

}
