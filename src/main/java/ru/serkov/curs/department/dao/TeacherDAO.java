package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;
import ru.serkov.curs.department.model.Teacher;
import ru.serkov.curs.department.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class TeacherDAO implements DAO<Teacher> {

    private  Connection connection =ConnectionDB.getConnection();


    public  List<Teacher> index()  {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from teachers";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Teacher teacher = new Teacher();

                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setDegree(resultSet.getString("degre"));


                teachers.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teachers;
    }

    public void add(Teacher teacher) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into teachers( name, degre) VALUES ( ?, ?)");
            preparedStatement.setString(1,teacher.getName());
            preparedStatement.setString(2,teacher.getDegree());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM teachers WHERE id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Teacher> show(int id) {
        List<Teacher> articles = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from teachers where id =?");
            preparedStatement.setInt(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher department = new Teacher();

                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                department.setDegree(resultSet.getString("degre"));


                articles.add(department);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(articles);
        return articles;
    }
}
