package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Tables {
    private final Connection connection = ConnectionDB.getConnection();


    @PostConstruct
    public void init() {
        departments();
        teachers();
        articles();
        subjects();
        plans();
    }

    private void departments() {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                                                                    CREATE TABLE IF NOT EXISTS departments (
                                                                                                                                                                                 id_department SERIAL PRIMARY KEY ,
                                                                                                                                                                                 name TEXT)
                            """);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void teachers() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS teachers
                    (
                        id_teacher SERIAL PRIMARY KEY,
                        name       TEXT,
                        degree     TEXT
                    )
                    """);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void subjects() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS subjects
                    (
                        id_subject    SERIAL PRIMARY KEY,
                        id_department INT,
                        name          TEXT
                    )
                    """);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void articles() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS articles
                    (
                        id_article SERIAL PRIMARY KEY,
                        id_teacher INT,
                        id_subject INT,
                        name       TEXT
                    )
                    """);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void plans() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    create table if not exists plans
                    (
                        id_plan     serial primary key,
                        id_article  integer,
                        start_date  date,
                        finish_date date,
                        count       integer,
                        state       boolean,
                        progress    integer
                    )
                    """);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
