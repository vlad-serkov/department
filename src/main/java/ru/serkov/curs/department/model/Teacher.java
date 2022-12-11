package ru.serkov.curs.department.model;

public class Teacher implements Model {
    private int id;
    private String name;
    private String degree;

    public Teacher() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
