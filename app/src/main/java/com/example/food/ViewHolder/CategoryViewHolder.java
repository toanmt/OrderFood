package com.example.food.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public ImageView imageView;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.menu_name);
        imageView = itemView.findViewById(R.id.menu_image);
    }
}