package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.crudstudent.databinding.ActivityUpdateBinding;
import com.example.crudstudent.model.Student;
import com.example.crudstudent.model.StudentDatabase;

public class UpdateActivity extends AppCompatActivity {
    private ActivityUpdateBinding binding;
    Student studentCurrent = new Student();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            binding.btnDelete.setVisibility(View.VISIBLE);
            binding.btnUpdate.setVisibility(View.VISIBLE);
            studentCurrent = (Student) bundle.getSerializable("student");
            initViewUpdate(studentCurrent);
        }else{
            binding.btnAdd.setVisibility(View.VISIBLE);
        }
        setListeners();
    }

    private void setListeners() {
        binding.btnAdd.setOnClickListener(v -> {
            Student student = new Student(binding.editName.getText().toString(),
                                binding.editStudentId.getText().toString(),
                                Double.parseDouble(binding.editGpa.getText().toString()));
            StudentDatabase.getInstance(this).addStudent(student);
            finish();
        });
        binding.btnDelete.setOnClickListener(v -> {
            StudentDatabase.getInstance(this).deleteStudent(studentCurrent);
            finish();
        });
        binding.btnUpdate.setOnClickListener(v -> {
            studentCurrent.setName(binding.editName.getText().toString());
            studentCurrent.setStudentID(binding.editStudentId.getText().toString());
            studentCurrent.setGpa(Double.parseDouble(binding.editGpa.getText().toString()));
            StudentDatabase.getInstance(this).updateStudent(studentCurrent);
            finish();
        });
    }

    private void initViewUpdate(Student student) {
        binding.editName.setText(student.getName());
        binding.editStudentId.setText(student.getStudentID());
        binding.editGpa.setText(student.getGpa()+"");
    }
}