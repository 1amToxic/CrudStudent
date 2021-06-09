package com.example.crudstudent.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String studentID;
    private double gpa;

    public Student() {
    }

    public Student(String name, String studentID, double gpa) {
        this.name = name;
        this.studentID = studentID;
        this.gpa = gpa;
    }

    public Student(int id, String name, String studentID, double gpa) {
        this.id = id;
        this.name = name;
        this.studentID = studentID;
        this.gpa = gpa;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
