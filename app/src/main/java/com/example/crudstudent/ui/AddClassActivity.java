package com.example.crudstudent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudstudent.R;
import com.example.crudstudent.databinding.ActivityAddClassBinding;
import com.example.crudstudent.model.ClassUni;
import com.example.crudstudent.model.MainDatabase;

public class AddClassActivity extends AppCompatActivity {
    ActivityAddClassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.themLop.setOnClickListener(v ->{
            String name = binding.editName.getText().toString();
            String des = binding.editDes.getText().toString();
            MainDatabase.getInstance(this).addClass(new ClassUni(name,des));
            Intent i = new Intent(this,ListClassActivity.class);
            i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }
}