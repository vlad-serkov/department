package ru.serkov.curs.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.serkov.curs.department.dao.DAO;
import ru.serkov.curs.department.dao.TeacherDAO;
import ru.serkov.curs.department.model.Teacher;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("teachers")
public class TeacherController implements Controller<Teacher> {
    DAO teacherDAO;
    @Autowired
    public TeacherController(@Qualifier("teacherDAO")DAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @GetMapping
    @Override
    public List<Teacher> index()  {
        return teacherDAO.index();
    }

    @Override
    public Map<String, List> indexByID(int id) {
        throw new UnsupportedOperationException();

    }

    @PostMapping
    public void add(@RequestBody Teacher teacher){
        teacherDAO.add(teacher);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id)  {
        teacherDAO.delete(id);
    }

}