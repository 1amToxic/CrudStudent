package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudstudent.R;
import com.example.crudstudent.adapter.StudentAdapter;
import com.example.crudstudent.databinding.ActivityListStudentBinding;
import com.example.crudstudent.model.MainDatabase;

public class ListStudentActivity extends AppCompatActivity {
    ActivityListStudentBinding binding;
    StudentAdapter studentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int isFeatures = getIntent().getIntExtra("is_features", 0);
        if (isFeatures == 1) {
            Toast.makeText(this, "Danh sach sinh vien ten la nam hoc nam 2", Toast.LENGTH_SHORT).show();
            studentAdapter = new StudentAdapter(this);
            studentAdapter.setListStudent(MainDatabase.getInstance(this).getStudentsWithFeatures());
            binding.recyclerStu.setAdapter(studentAdapter);
            binding.recyclerStu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            binding.addStudent.setVisibility(View.GONE);
        } else {
            studentAdapter = new StudentAdapter(this);
            studentAdapter.setListStudent(MainDatabase.getInstance(this).getAllStudent());
            binding.recyclerStu.setAdapter(studentAdapter);
            binding.recyclerStu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            binding.addStudent.setOnClickListener(v -> startActivity(new Intent(this, AddStudentActivity.class)));
        }
    }
}