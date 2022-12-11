package ru.serkov.curs.department.controller;

import org.springframework.web.bind.annotation.*;
import ru.serkov.curs.department.model.Model;

import java.util.List;
import java.util.Map;

public interface Controller<E extends Model> {
    List<?> index();
    Map<String, List> indexByID(int id);

    @PostMapping
    void add(@RequestBody E e);
    @DeleteMapping("Id")
    void delete(@PathVariable() int id);

}
