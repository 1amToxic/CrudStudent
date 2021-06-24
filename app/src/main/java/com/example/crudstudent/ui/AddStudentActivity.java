package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudstudent.R;
import com.example.crudstudent.databinding.ActivityAddStudentBinding;
import com.example.crudstudent.model.MainDatabase;
import com.example.crudstudent.model.Student;

public class AddStudentActivity extends AppCompatActivity {
    ActivityAddStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.themSinhvien.setOnClickListener(v -> {
            String name = binding.editName.getText().toString();
            String yearOB = binding.editYearOB.getText().toString();
            String homeTown = binding.editHome.getText().toString();
            String year = binding.spinner.getSelectedItem().toString();
            MainDatabase.getInstance(this).addStudent(new Student(name,
                    Integer.parseInt(yearOB),
                    homeTown,
                    Integer.parseInt(year)));
            Intent i = new Intent(this,ListStudentActivity.class);
            i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }
}