package jdbc.repository;

import java.sql.SQLException;

public class SubjectGroupDML extends TableBaseOperations implements CRUD {

    public SubjectGroupDML() throws SQLException {
        super("subject_group");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS " + tableName +
                        "(subjects_id INT UNSIGNED NOT NULL, " +
                        "groups_st_specialty INT UNSIGNED NOT NULL, " +
                        "PRIMARY KEY (subjects_id, groups_st_specialty));",
                "Создана таблица " + tableName);

    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement(" ALTER TABLE " + tableName +
                        " ADD CONSTRAINT subjects_id_fk" +
                        " FOREIGN KEY (subjects_id) REFERENCES subjects(subject_id)" +
                        " ON DELETE CASCADE ON UPDATE CASCADE;",
                "Cоздан внешний ключ subjects_groups -> subjects.subject_id");
        super.executeSqlStatement(" ALTER TABLE " + tableName +
                        " ADD CONSTRAINT groups_id_fk" +
                        " FOREIGN KEY (groups_st_specialty) REFERENCES groups_st(group_id)" +
                        " ON DELETE CASCADE ON UPDATE CASCADE;",
                "Cоздан внешний ключ subjects_groups -> groups_st.group_id");
    }

    public void insertValues(String subject, String type, String specialty) throws SQLException {
        super.executeSqlStatement("INSERT INTO " + tableName +
                        "(subjects_id, groups_st_specialty) " +
                        "VALUES ((SELECT subject_id FROM subjects WHERE title = '"+ subject + "' AND type = '" + type + "')," +
                        "(SELECT group_id FROM groups_st WHERE specialty = '" + specialty + "'));",
                "Добавлены данные в таблицу " + tableName);
    }
}
