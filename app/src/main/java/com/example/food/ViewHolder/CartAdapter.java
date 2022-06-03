package com.example.food.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Model.Order;
import com.example.food.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> orderList = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.txtName.setText(orderList.get(position).getProductName());
        holder.txtPrice.setText(orderList.get(position).getPrice());
        holder.txtNumberOrder.setText(orderList.get(position).getQuantity());
        holder.txtTotal.setText(Integer.parseInt(orderList.get(position).getQuantity()) * Integer.parseInt(orderList.get(position).getPrice()) +"");
        Picasso.with(context).load(orderList.get(position).getImage()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
