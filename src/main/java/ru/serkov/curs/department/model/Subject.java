package ru.serkov.curs.department.model;

public class Subject implements Model {
    private int id;
    private int id_department;
    private String name;



    public Subject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", id_department=" + id_department +
                ", name='" + name + '\'' +
                '}';
    }
}
