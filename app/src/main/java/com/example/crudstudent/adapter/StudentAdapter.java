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
import com.example.crudstudent.model.Student;
import com.example.crudstudent.ui.UpdateActivity;

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

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull StudentAdapter.StudentViewHolder holder, int position) {
        holder.bind(listStudent.get(position));
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvStudentId,tvGpa;
        public StudentViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStudentId = itemView.findViewById(R.id.tv_student_id);
            tvGpa = itemView.findViewById(R.id.tv_gpa);
        }
        void bind(Student student){
            tvName.setText(student.getName());
            tvStudentId.setText(student.getStudentID());
            tvGpa.setText(student.getGpa()+"");
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student",student);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            });
        }
    }
}
