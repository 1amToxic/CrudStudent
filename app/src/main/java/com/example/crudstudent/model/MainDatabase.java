package com.example.crudstudent.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class MainDatabase extends SQLiteOpenHelper {
    private static MainDatabase instance;

    public static MainDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new MainDatabase(context);
        }
        return instance;
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Main.db";
    //student
    public static final String TABLE_NAME_STU = "student";
    public static final String S_ID = "id";
    public static final String S_NAME = "name";
    public static final String S_YEAROB = "yearob";
    public static final String S_HOMETOWN = "hometown";
    public static final String S_YEAR = "year";
    //class
    public static final String TABLE_NAME_CLASS = "classuni";
    public static final String C_ID = "id";
    public static final String C_NAME = "name";
    public static final String C_DES = "des";
    //student in class
    public static final String TABLE_NAME_STU_CLASS = "stuclass";
    public static final String SC_ID = "id";
    public static final String SC_ID_STU = "idStu";
    public static final String SC_ID_CLA = "idClass";

    private static final String SQL_CREATE_STU =
            "CREATE TABLE " + TABLE_NAME_STU + " (" +
                    S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    S_NAME + " TEXT," +
                    S_YEAROB + " INTEGER," +
                    S_HOMETOWN + " TEXT, " +
                    S_YEAR + " INTEGER)";
    private static final String SQL_CREATE_CLASS =
            "CREATE TABLE " + TABLE_NAME_CLASS + " (" +
                    C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    C_NAME + " TEXT," +
                    C_DES + " TEXT)";
    private static final String SQL_CREATE_STU_IN_CLASS =
            "CREATE TABLE " + TABLE_NAME_STU_CLASS + " (" +
                    SC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SC_ID_STU + " INTEGER," +
                    SC_ID_CLA + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME_STU;

    private MainDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STU);
        db.execSQL(SQL_CREATE_CLASS);
        db.execSQL(SQL_CREATE_STU_IN_CLASS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(S_NAME, student.getName());
        values.put(S_YEAROB, student.getYearOB());
        values.put(S_HOMETOWN, student.getHometown());
        values.put(S_YEAR, student.getYear());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME_STU, null, values);
        db.close();
        return newRowId;
    }

    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_STU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setYearOB(cursor.getInt(2));
                student.setHometown(cursor.getString(3));
                student.setYear(cursor.getInt(4));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }

    public long addClass(ClassUni classUni) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(C_NAME, classUni.getName());
        values.put(C_DES, classUni.getDes());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME_CLASS, null, values);
        db.close();
        return newRowId;
    }

    public List<ClassUni> getAllClass() {
        List<ClassUni> listStudent = new ArrayList<>();
//        String selectQuery = "SELECT  c.id,c.name,c.des,COUNT(s.id) FROM " + TABLE_NAME_CLASS+" as c,"
//                +TABLE_NAME_STU+" as s GROUP BY c.id";
        String selectQuery = "Select * from " + TABLE_NAME_CLASS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ClassUni classUni = new ClassUni();
                classUni.setId(cursor.getInt(0));
                classUni.setName(cursor.getString(1));
                classUni.setDes(cursor.getString(2));
//                classUni.setNumberStu(cursor.getInt(3));
                listStudent.add(classUni);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }

    public long addStudentToClass(ClassUni classUni, Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SC_ID_CLA, classUni.getId());
        values.put(SC_ID_STU, student.getId());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME_STU_CLASS, null, values);
        db.close();
        return newRowId;
    }

    public List<Student> getStudentsInClass(ClassUni classUni) {
        List<Student> listStudent = new ArrayList<>();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String selectQuery = "SELECT * FROM " + TABLE_NAME_STU + " as s," + TABLE_NAME_STU_CLASS + " as sc " +
                "WHERE s.id = sc.idStu and sc.idClass = " + classUni.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setYearOB(cursor.getInt(2));
                student.setHometown(cursor.getString(3));
                student.setYear(cursor.getInt(4));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }

    public List<Student> getStudentsWithFeatures() {
        List<Student> listStudent = new ArrayList<>();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String s = "Nam";
        String selectQuery = "SELECT * FROM " + TABLE_NAME_STU +
                " WHERE " + S_NAME + " = '" + s + "' and " + S_YEAR + " = 2";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setYearOB(cursor.getInt(2));
                student.setHometown(cursor.getString(3));
                student.setYear(cursor.getInt(4));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }
//    public List<Student> searchStudentByName(String s){
//        List<Student> listStudent = new ArrayList<>();
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE "+STUDENT_NAME+" LIKE '%"+s+"%'";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Student student = new Student();
//                student.setId(cursor.getInt(0));
//                student.setName(cursor.getString(1));
//                student.setStudentID(cursor.getString(2));
//                student.setGpa(cursor.getDouble(3));
//                listStudent.add(student);
//            } while (cursor.moveToNext());
//        }
//        return listStudent;
//    }
//    public int deleteStudent(Student student){
//        SQLiteDatabase db = this.getWritableDatabase();
//        int deleteRow = db.delete(TABLE_NAME, ID + " = ?",
//                new String[] { String.valueOf(student.getId()) });
//        db.close();
//        return deleteRow;
//    }
//    public int updateStudent(Student student){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(STUDENT_NAME, student.getName());
//        values.put(STUDENT_ID, student.getStudentID());
//        values.put(STUDENT_GPA, student.getGpa());
//
//        // updating row
//        return db.update(TABLE_NAME, values, ID + " = ?",
//                new String[]{String.valueOf(student.getId())});
//    }
}
