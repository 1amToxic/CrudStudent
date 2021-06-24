package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudstudent.adapter.ClassAdapter;
import com.example.crudstudent.databinding.ActivityListClassBinding;
import com.example.crudstudent.model.MainDatabase;

public class ListClassActivity extends AppCompatActivity {
    ActivityListClassBinding binding;
    ClassAdapter classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        classAdapter = new ClassAdapter(this);
        classAdapter.setListClass(MainDatabase.getInstance(this).getAllClass());

        binding.recyclerClass.setAdapter(classAdapter);
        binding.recyclerClass.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.addClass.setOnClickListener(v -> startActivity(new Intent(this, AddClassActivity.class)));
    }
}