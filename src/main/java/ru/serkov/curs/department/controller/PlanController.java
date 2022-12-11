package ru.serkov.curs.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.serkov.curs.department.dao.DAO;
import ru.serkov.curs.department.model.Plan;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("plans")
public class PlanController implements Controller<Plan>{
    DAO planDAO;

    @Autowired
    public PlanController( DAO planDAO) {
        this.planDAO = planDAO;
    }

    @Override
    @GetMapping
    public List<?> index() {
        return planDAO.index();
    }

    @Override
    public Map<String, List> indexByID(int id) {
        return null;
    }

    @Override
    @PostMapping
    public void add(Plan plan) {

        planDAO.add(plan);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        planDAO.delete(id);
    }
}
