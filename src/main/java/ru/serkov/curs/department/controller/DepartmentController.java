package ru.serkov.curs.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.serkov.curs.department.dao.ArticleDAO;
import ru.serkov.curs.department.dao.DAO;
import ru.serkov.curs.department.dao.DepartmentDAO;
import ru.serkov.curs.department.model.Department;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("department")
public class DepartmentController implements Controller<Department>{
    DAO departmentDAO;

    @Autowired
    public DepartmentController(@Qualifier("departmentDAO") DAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @GetMapping
    @Override
    public List<Department> index()  {
        return departmentDAO.index();
    }

    @Override
    public Map<String, List> indexByID(int id) {
        throw new UnsupportedOperationException();

    }

    @PostMapping
    public void add(@RequestBody Department department){
        departmentDAO.add(department);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id)  {
        System.out.println(id);
        departmentDAO.delete(id);
    }
}
