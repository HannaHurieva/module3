package jdbc.service;

import jdbc.repository.GroupsStDML;
import jdbc.repository.SubjectGroupDML;
import jdbc.repository.SubjectsDML;
import jdbc.repository.TeachersDML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ScheduleConnectDB {
    // Блок объявления констант
    public static final String DB_URL = "jdbc:mysql://localhost:3306/schedule";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "password";

    // Таблицы СУБД
    TeachersDML teachers;
    SubjectsDML subjects;
    GroupsStDML groupsSt;
    SubjectGroupDML subjectGroup;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    // Инициализация
    public ScheduleConnectDB() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        // Инициализируем таблицы
        teachers = new TeachersDML();
        subjects = new SubjectsDML();
        groupsSt = new GroupsStDML();
        subjectGroup = new SubjectGroupDML();
    }

    // Создание всех таблиц и ключей между ними
    public void createTablesAndForeignKeys() throws SQLException {
        teachers.createTable();
        subjects.createTable();
        groupsSt.createTable();
        subjectGroup.createTable();
        // Создание внешних ключей (связи между таблицами)
        subjects.createForeignKeys();
        subjectGroup.createForeignKeys();
    }

    public void insertData() throws SQLException {
        teachers.insertValues("Pensov", "Serg", "First", "999-999", 8);
        teachers.insertValues("Slyusar", "Peter", "Second", "777-777", 5);
        teachers.insertValues("Shmorgun", "Eugene", "Third", "888-888", 10);

        subjects.insertValues("Информатика", "Лекция", 50, 200, "Shmorgun");
        subjects.insertValues("Информатика", "Практика", 25, 300, "Pensov");
        subjects.insertValues("Математика", "Лекция", 90, 150, "Pensov");
        subjects.insertValues("Математика", "Практика", 60, 250, "Slyusar");
        subjects.insertValues("Базы данных", "Лекция", 40, 250, "Slyusar");
        subjects.insertValues("Базы данных", "Практика", 60, 200, "Shmorgun");

        groupsSt.insertValues("J2", "comp", "on weekdays", 12);
        groupsSt.insertValues("J4", "math", "evenings daily", 15);
        groupsSt.insertValues("J7", "web", "on weekends", 10);

        subjectGroup.insertValues("Информатика", "Лекция", "comp");
        subjectGroup.insertValues("Информатика", "Практика", "comp");
        subjectGroup.insertValues("Математика", "Лекция", "comp");
        subjectGroup.insertValues("Математика", "Практика", "comp");
        subjectGroup.insertValues("Базы данных", "Лекция", "comp");

        subjectGroup.insertValues("Математика", "Лекция", "math");
        subjectGroup.insertValues("Математика", "Практика", "math");
        subjectGroup.insertValues("Базы данных", "Практика", "math");

        subjectGroup.insertValues("Информатика", "Лекция", "web");
        subjectGroup.insertValues("Информатика", "Практика", "web");
        subjectGroup.insertValues("Базы данных", "Лекция", "web");
    }

    public void createQuerySQL() throws SQLException {
        groupsSt.getSortingByGroupsTitlesWithNumberOfStudents();
        teachers.getTeacherByExperience(5);
        teachers.getTeachersGroupsList();
        teachers.getCostHoursByTeacher("Pensov");
        teachers.getTeachersByExperienceAndSubjects(5, "Математика", "Информатика");
        groupsSt.getSubjectsHoursListBySpeciality();
    }

}
