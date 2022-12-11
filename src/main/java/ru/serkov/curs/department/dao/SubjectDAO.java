package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;
import ru.serkov.curs.department.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectDAO implements DAO<Subject> {
    private final Connection connection =ConnectionDB.getConnection();

    public List<Subject> index(int id)  {
        List<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from subjects where id_department =?");
            preparedStatement.setInt(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                Subject subject = new Subject();

                subject.setId_department(resultSet.getInt("id_department"));
                subject.setId(resultSet.getInt("id_subject"));
                subject.setName(resultSet.getString("name"));


                subjects.add(subject);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return subjects;
    }

    @Override
    public List<Subject> show(int id) {
        return null;
    }

    public void add(Subject subject) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into subjects( id_department,name) VALUES ( ?,?)");
            preparedStatement.setInt(1,subject.getId_department());
            preparedStatement.setString(2,subject.getName());
            System.out.println(subject);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM subjects WHERE id_subject=?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
