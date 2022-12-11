package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/universaty";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            DriverManager.getConnection(URL, USERNAME, PASSWORD).close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {

        System.out.println("GG");
        System.out.println(connection);
        return connection;
    }

    private ConnectionDB() {
    }
}
