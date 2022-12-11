package ru.serkov.curs.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.serkov.curs.department.dao.DAO;
import ru.serkov.curs.department.model.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("article")
public class ArticleController implements Controller<Article> {
    DAO articleDAO;
    DAO teacherDAO;
    DAO departmentDAO;

    @Autowired
    public ArticleController(DAO articleDAO, DAO teacherDAO, DAO departmentDAO) {
        this.articleDAO = articleDAO;
        this.teacherDAO = teacherDAO;
        this.departmentDAO = departmentDAO;
    }

    @Override
    @GetMapping
    public List<?> index() {
        return articleDAO.index();
    }

    @Override
    @GetMapping("/{id}")
    public Map<String, List> indexByID(@PathVariable("id") int id)  {
        Map<String, List> json = new HashMap<String, List>();
        json.put("articles",articleDAO.index(id));
        json.put("teachers",teacherDAO.show(id));
        json.put("departments", departmentDAO.index());
        return json;
    }

    @Override
    @PostMapping("/{id}")
    public void add(@RequestBody Article article){
        articleDAO.add(article);
    }
    @Override
    @DeleteMapping("/{id}/{id_article}")
    public void delete(@PathVariable("id_article") int id)  {
        articleDAO.delete(id);
    }



}
