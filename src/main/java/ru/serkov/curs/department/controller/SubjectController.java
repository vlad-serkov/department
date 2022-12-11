package ru.serkov.curs.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.serkov.curs.department.dao.DAO;
import ru.serkov.curs.department.dao.DepartmentDAO;
import ru.serkov.curs.department.dao.SubjectDAO;
import ru.serkov.curs.department.model.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("subjects/{id}")
public class SubjectController implements Controller<Subject>{
    DAO subjectDAO;
    DAO departmentDAO;

    @Autowired
    public SubjectController(@Qualifier("subjectDAO") DAO subjectDAO, @Qualifier("departmentDAO") DAO departmentDAO) {
        this.subjectDAO = subjectDAO;
        this.departmentDAO = departmentDAO;
    }


    @Override
    public List<?> index() {
        throw new UnsupportedOperationException();

    }

    @Override
    @GetMapping
    public Map<String,List> indexByID(@PathVariable("id") int id)  {
        Map<String, List> json = new HashMap<String, List>();
        json.put("subjects",subjectDAO.index(id));
        json.put("departments",departmentDAO.show(id));

        return json;
    }

    @PostMapping
    public void add(@RequestBody Subject subject){
        subjectDAO.add(subject);
    }
    @DeleteMapping("/{id_subject}")
    public void delete(@PathVariable("id_subject") int id)  {
        subjectDAO.delete(id);
    }
}
