package ru.serkov.curs.department.dao;

import ru.serkov.curs.department.model.Model;

import java.util.List;

public interface DAO<E extends Model> {
    default List<?> index() {
        throw new UnsupportedOperationException();
    }

    default List<?> index(int id) {
        throw new UnsupportedOperationException();
    }

    void add(E o);

    void delete(int id);


    List<E> show(int id);
}
