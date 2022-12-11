package ru.serkov.curs.department.dao;

import org.springframework.stereotype.Component;
import ru.serkov.curs.department.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class ArticleDAO implements DAO<Article>{
    private final Connection connection =ConnectionDB.getConnection();

    public List<Article.Appearance> index(int id)  {
        List<Article.Appearance> articles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                    select id_article,articles.name,s.name,d.name
                                                from articles
                                                inner join subjects s on articles.id_subject = s.id_subject
                                                inner join departments d on s.id_department = d.id_department
                                                where id_teacher =?
"""
                    );

            preparedStatement.setInt(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article.Appearance appearance = Article.getArticleAppearance();
                appearance.setId(resultSet.getInt("id_article"));
                appearance.setArticleName(resultSet.getString(2));
                appearance.setSubjectName(resultSet.getString(3));
                appearance.setDepartmentName(resultSet.getString(4));

                articles.add(appearance);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return articles;
    }

    @Override
    public List<Article> show(int id) {
        return null;
    }

    public void add(Article article) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into articles( id_teacher, id_subject,name) VALUES ( ?,?,?)");
            preparedStatement.setInt(1,article.getId_teacher());
            preparedStatement.setInt(2,article.getId_subject());
            preparedStatement.setString(3, article.getName());
            System.out.println(article);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM articles WHERE id_article=?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Article.Appearance> index() {
        List<Article.Appearance> articles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                    select id_article,articles.name,s.name,d.name
                                                from articles
                                                inner join subjects s on articles.id_subject = s.id_subject
                                                inner join departments d on s.id_department = d.id_department
                                                
                                                
"""
                    );



            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article.Appearance appearance = Article.getArticleAppearance();
                appearance.setId(resultSet.getInt("id_article"));
                appearance.setArticleName(resultSet.getString(2));
                appearance.setSubjectName(resultSet.getString(3));
                appearance.setDepartmentName(resultSet.getString(4));

                articles.add(appearance);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return articles;
    }
    }

