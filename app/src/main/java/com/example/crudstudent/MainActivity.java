package com.example.crudstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudstudent.adapter.StudentAdapter;
import com.example.crudstudent.databinding.ActivityMainBinding;
import com.example.crudstudent.model.Student;
import com.example.crudstudent.model.MainDatabase;
import com.example.crudstudent.ui.ListClassActivity;
import com.example.crudstudent.ui.ListStudentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnClass.setOnClickListener(v ->
                startActivity(new Intent(this, ListClassActivity.class)));
        binding.btnStudent.setOnClickListener(v -> {
                    Intent intent = new Intent(this, ListStudentActivity.class);
                    intent.putExtra("is_features", 0);
                    startActivity(intent);
                }
        );
        binding.btnFeatures.setOnClickListener(v -> {
                    Intent intent = new Intent(this, ListStudentActivity.class);
                    intent.putExtra("is_features", 1);
                    startActivity(intent);
                }
        );

    }
}