package com.example.crudstudent.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudstudent.databinding.ItemClassBinding;
import com.example.crudstudent.model.ClassUni;
import com.example.crudstudent.ui.DetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private Context context;

    public ClassAdapter(Context context) {
        this.context = context;
    }

    private List<ClassUni> listClass = new ArrayList<>();

    public List<ClassUni> getListClass() {
        return listClass;
    }

    public void setListClass(List<ClassUni> listClass) {
        this.listClass = listClass;
    }

    @NonNull
    @NotNull
    @Override
    public ClassAdapter.ClassViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ClassViewHolder(ItemClassBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClassAdapter.ClassViewHolder holder, int position) {
        holder.bind(listClass.get(position));
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        private ItemClassBinding binding;
        public ClassViewHolder(@NonNull @NotNull ItemClassBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(ClassUni classUni){
            binding.tvName.setText(classUni.getName()+" Co: "+classUni.getNumberStu()+" Sinh vien");
            binding.tvDes.setText(classUni.getDes());
            binding.getRoot().setOnClickListener(v ->{
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("class",classUni);
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }
    }
}
