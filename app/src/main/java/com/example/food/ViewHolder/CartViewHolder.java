package com.example.food.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class CartViewHolder extends RecyclerView.ViewHolder{

    public TextView txtName, txtNumberOrder, txtPrice, txtTotal;
    public ImageView imageView;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.titleTxt);
        txtNumberOrder = itemView.findViewById(R.id.numberItemTxt);
        txtPrice = itemView.findViewById(R.id.feeEachItem);
        txtTotal = itemView.findViewById(R.id.totalEachItem);
        imageView = itemView.findViewById(R.id.pic);
    }
}
