package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudstudent.R;
import com.example.crudstudent.databinding.ActivityAddStudentInClassBinding;
import com.example.crudstudent.model.ClassUni;
import com.example.crudstudent.model.MainDatabase;
import com.example.crudstudent.model.Student;

public class AddStudentInClassActivity extends AppCompatActivity {
    ActivityAddStudentInClassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentInClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ClassUni classUni = (ClassUni) getIntent().getSerializableExtra("class");
        binding.button.setOnClickListener(v -> {
            int id = Integer.parseInt(binding.editIDSV.getText().toString());
            Student student = new Student();
            student.setId(id);
            MainDatabase.getInstance(this).addStudentToClass(classUni,student);
            Intent intent = new Intent(this,DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("class",classUni);
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}