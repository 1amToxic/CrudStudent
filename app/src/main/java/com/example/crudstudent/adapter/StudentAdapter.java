package com.example.crudstudent.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudstudent.R;
import com.example.crudstudent.databinding.ItemStudentBinding;
import com.example.crudstudent.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> listStudent = new ArrayList<>();
    private Context mContext;

    public StudentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull StudentAdapter.StudentViewHolder holder, int position) {
        holder.bind(listStudent.get(position));
        //10 di qua 10 lan
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        ItemStudentBinding bindingS;
        public StudentViewHolder(ItemStudentBinding binding) {
            super(binding.getRoot());
            bindingS = binding;
        }
        void bind(Student student){
            bindingS.tvName.setText(student.getName());
            bindingS.tvHometown.setText(student.getHometown());
            bindingS.tvYear.setText(student.getYear()+"");
            bindingS.tvYearOb.setText(student.getYearOB()+"");
        }
    }
}
