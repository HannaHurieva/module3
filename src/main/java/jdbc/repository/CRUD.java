package jdbc.repository;

import java.sql.SQLException;

// Операции с таблицами
public interface CRUD {
    void createTable() throws SQLException; // создание таблицы
    void createForeignKeys() throws SQLException; // создание связей между таблицами
    //void insertValues() throws SQLException; // добавление данных в таблицу

}
