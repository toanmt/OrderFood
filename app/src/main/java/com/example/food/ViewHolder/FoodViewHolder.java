package com.example.food.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class FoodViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public ImageView imageView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.food_name);
        imageView = itemView.findViewById(R.id.food_image);
    }
}
