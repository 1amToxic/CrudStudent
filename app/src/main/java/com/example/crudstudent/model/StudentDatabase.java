package com.example.crudstudent.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabase extends SQLiteOpenHelper {
    private static StudentDatabase instance;
    public static StudentDatabase getInstance(Context context){
        if(instance == null){
            instance = new StudentDatabase(context);
        }
        return instance;
    }
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Student1.db";
    public static final String TABLE_NAME = "student";
    public static final String ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_ID = "sid";
    public static final String STUDENT_GPA = "gpa";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +  TABLE_NAME +" (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    STUDENT_NAME + " TEXT," +
                    STUDENT_ID + " TEXT,"+
                    STUDENT_GPA + " REAL)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    private StudentDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public long addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_ID, student.getStudentID());
        values.put(STUDENT_GPA, student.getGpa());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }
    public List<Student> getAll(){
        List<Student> listStudent = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setStudentID(cursor.getString(2));
                student.setGpa(cursor.getDouble(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }
    public List<Student> searchStudentByName(String s){
        List<Student> listStudent = new ArrayList<>();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE "+STUDENT_NAME+" LIKE '%"+s+"%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setStudentID(cursor.getString(2));
                student.setGpa(cursor.getDouble(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        return listStudent;
    }
    public int deleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        int deleteRow = db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
        return deleteRow;
    }
    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_ID, student.getStudentID());
        values.put(STUDENT_GPA, student.getGpa());

        // updating row
        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[]{String.valueOf(student.getId())});
    }
}
