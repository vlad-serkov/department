package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;
import ru.serkov.curs.department.model.Plan;
import ru.serkov.curs.department.model.Model;
import ru.serkov.curs.department.model.Plan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlanDAO implements DAO<Plan>{
    private final Connection connection =ConnectionDB.getConnection();
    @Override
    public List index() {
        List<Plan.Appearance> plans = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = """
                select id_plan,start_date,finish_date,count,state,progress,a.name,s.name,t.name,d.name from plans
                        inner join articles a on plans.id_article = a.id_article
                        inner join subjects s on a.id_subject = s.id_subject
                        inner join teachers t on a.id_teacher = t.id
                        inner join departments d on s.id_department = d.id_department
""";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Plan.Appearance appearance = Plan.getPlanAppearance(
                        resultSet.getInt(1),
                        resultSet.getDate(2),
                        resultSet.getDate(3),
                        resultSet.getInt(4),
                        resultSet.getInt(6),
                        resultSet.getBoolean(5),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(10),
                        resultSet.getString(9)

                );

                plans.forEach(System.out::println);
                plans.add(appearance);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return plans;
    }


    @Override
    public void add(Plan plan) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into plans( id_article, start_date,finish_date,count,state, progress) VALUES ( ?,?,?,?,?,?)");
            preparedStatement.setInt(1,plan.getId_article());
            preparedStatement.setDate(2, java.sql.Date.valueOf(plan.getStart_date()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(plan.getFinish_date()));
            preparedStatement.setInt(4,  plan.getCount());
            preparedStatement.setBoolean(5,  plan.isState());
            preparedStatement.setInt(6,  plan.getProgress());


            System.out.println(plan);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM plans WHERE id_plan = ?");
            preparedStatement.setInt(1,  id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List show(int id) {
        return null;
    }
}
