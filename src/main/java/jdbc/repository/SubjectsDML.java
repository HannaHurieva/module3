package jdbc.repository;

import java.sql.SQLException;

public class SubjectsDML extends TableBaseOperations implements CRUD {

    public SubjectsDML() throws SQLException {
        super("subjects");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS " + tableName +
                        "(subject_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
                        "title VARCHAR(255) NOT NULL, " +
                        "type VARCHAR(255), " +
                        "hours INT UNSIGNED DEFAULT 0, " +
                        "cost_per_hour INT UNSIGNED DEFAULT 0, " +
                        "teacher_id INT UNSIGNED);",
                        "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement(" ALTER TABLE " + tableName +
                        " ADD CONSTRAINT subjects_teachers_fk" +
                        " FOREIGN KEY (teacher_id) REFERENCES teachers(id)" +
                        " ON DELETE CASCADE ON UPDATE CASCADE;",
                "Cоздан внешний ключ subjects.teachers -> teachers.id");
    }

    public void insertValues(String title, String type, int hours, int cost_per_hour, String lastName) throws SQLException {
        super.executeSqlStatement("INSERT INTO " + tableName +
                        "(title, type, hours, cost_per_hour, teacher_id) " +
                        "VALUES ('" + title + "', '" + type + "', " + hours + ", " + cost_per_hour + ", " +
                        "(SELECT id FROM teachers WHERE last_name = '" + lastName + "'));",
                "Добавлены данные в таблицу " + tableName);
    }
}
