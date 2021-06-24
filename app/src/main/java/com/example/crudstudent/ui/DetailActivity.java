package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudstudent.R;
import com.example.crudstudent.adapter.StudentAdapter;
import com.example.crudstudent.databinding.ActivityDetailBinding;
import com.example.crudstudent.model.ClassUni;
import com.example.crudstudent.model.MainDatabase;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ClassUni classUni = (ClassUni) getIntent().getSerializableExtra("class");
        studentAdapter = new StudentAdapter(this);
        studentAdapter.setListStudent(MainDatabase.getInstance(this).getStudentsInClass(classUni));
        binding.recyclerStuInClass.setAdapter(studentAdapter);
        binding.recyclerStuInClass.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.addStudent.setOnClickListener(v -> {
            Intent intent = new Intent(this,AddStudentInClassActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("class",classUni);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}