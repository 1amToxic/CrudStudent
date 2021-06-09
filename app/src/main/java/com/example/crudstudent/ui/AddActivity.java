package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.crudstudent.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}