package com.example.crudstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.crudstudent.adapter.StudentAdapter;
import com.example.crudstudent.databinding.ActivityMainBinding;
import com.example.crudstudent.model.Student;
import com.example.crudstudent.model.StudentDatabase;
import com.example.crudstudent.ui.AddActivity;
import com.example.crudstudent.ui.UpdateActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StudentAdapter studentAdapter;
    private List<Student> listStudent = new ArrayList<>();

    @Override
    protected void onRestart() {
        super.onRestart();
        listStudent = StudentDatabase.getInstance(this).getAll();
        studentAdapter.setListStudent(listStudent);
        studentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        studentAdapter = new StudentAdapter(this);
        listStudent = StudentDatabase.getInstance(this).getAll();
        studentAdapter.setListStudent(listStudent);
        binding.recyclerStudent.setAdapter(studentAdapter);
        binding.recyclerStudent.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.fabAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UpdateActivity.class)));
        binding.svStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listStudent = StudentDatabase.getInstance(MainActivity.this).searchStudentByName(newText);
                studentAdapter.setListStudent(listStudent);
                studentAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}