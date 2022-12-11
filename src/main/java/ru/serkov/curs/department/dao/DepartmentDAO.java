package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;
import ru.serkov.curs.department.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DepartmentDAO implements DAO<Department> {

    private final Connection connection =ConnectionDB.getConnection();
    public List<Department> index()  {
        List<Department> departments = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from departments";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Department department = new Department();

                department.setId(resultSet.getInt("id_department"));
                department.setName(resultSet.getString("name"));


                departments.add(department);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return departments;
    }

    public void add(Department department){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into departments( name) VALUES ( ?)");
            preparedStatement.setString(1,department.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM departments WHERE id_department = ?");

            preparedStatement.setInt(1,  id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public List<Department> show(int id) {
        List<Department> departments = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from departments where id_department =?");
            preparedStatement.setInt(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();

                department.setId(resultSet.getInt("id_department"));
                department.setName(resultSet.getString("name"));


                departments.add(department);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return departments;
    }
    

}
