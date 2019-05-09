package jdbc;

import jdbc.service.ScheduleConnectDB;

import java.sql.SQLException;

public class MainJDBC {
    public static void main(String[] args) {
        try {
            ScheduleConnectDB ConnectionToDB = new ScheduleConnectDB();
            ConnectionToDB.createTablesAndForeignKeys();
            ConnectionToDB.insertData();
            ConnectionToDB.createQuerySQL();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
