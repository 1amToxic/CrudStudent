package com.example.crudstudent.model;

public class StudentInClassUni {
    private int id;
    private int idStudent;
    private int idClass;

    public StudentInClassUni() {
    }

    public StudentInClassUni(int idStudent, int idClass) {
        this.idStudent = idStudent;
        this.idClass = idClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }
}
