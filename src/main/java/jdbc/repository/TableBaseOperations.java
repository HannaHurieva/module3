package jdbc.repository;


import jdbc.service.ScheduleConnectDB;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Сервисный родительский класс, куда вынесена реализация общих действий для всех таблиц
public class TableBaseOperations implements Closeable {
    Connection connection;  // JDBC-соединение для работы с таблицей
    String tableName;       // Имя таблицы

    public TableBaseOperations() {
    }

    TableBaseOperations(String tableName) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.connection = ScheduleConnectDB.getConnection(); // Установим соединение с СУБД для дальнейшей работы
    }


    // Закрытие
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    // Выполнить SQL команду без параметров в СУБД, по завершению выдать сообщение в консоль
    void executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        statement.execute(sql); // Выполняем statement - sql команду
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
        if (description != null)
            System.out.println(description);
    }


    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    }

    void executeQuerySql(String sql, String description, int[] index) throws SQLException {
        if (description != null) {
            System.out.println();
            System.out.println(description);
        }
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            for (int i = 1; i <= index.length; i++) {
                if (i == 1) {
                    System.out.println();
                    System.out.print(result.getString(index[i - 1]));
                } else {
                    System.out.print("   " + result.getString(index[i - 1]));
                }
            }
        }
        System.out.println();
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
    }


    // Активизация соединения с СУБД, если оно не активно.
    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ScheduleConnectDB.getConnection();
        }
    }
}