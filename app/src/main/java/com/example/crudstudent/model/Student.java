package com.example.crudstudent.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int yearOB;
    private String hometown;
    private int year;

    public Student() {
    }

    public Student(String name, int yearOB, String hometown, int year) {
        this.name = name;
        this.yearOB = yearOB;
        this.hometown = hometown;
        this.year = year;
    }

    public Student(int id, String name, int yearOB, String hometown, int year) {
        this.id = id;
        this.name = name;
        this.yearOB = yearOB;
        this.hometown = hometown;
        this.year = year;
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

    public int getYearOB() {
        return yearOB;
    }

    public void setYearOB(int yearOB) {
        this.yearOB = yearOB;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
