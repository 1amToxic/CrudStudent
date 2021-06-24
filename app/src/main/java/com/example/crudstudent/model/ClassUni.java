package com.example.crudstudent.model;

import java.io.Serializable;
import java.util.List;

public class ClassUni implements Serializable {
    private int id;
    private String name;
    private String des;
    private List<Student> list;
    private int numberStu;
    public ClassUni() {
    }

    public ClassUni(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public ClassUni(int id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
    }

    public int getNumberStu() {
        return numberStu;
    }

    public void setNumberStu(int numberStu) {
        this.numberStu = numberStu;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
