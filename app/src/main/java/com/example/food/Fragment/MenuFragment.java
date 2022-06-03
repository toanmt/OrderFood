package com.example.food.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Activity.FoodListActivity;
import com.example.food.Model.Menu;
import com.example.food.R;
import com.example.food.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class MenuFragment extends Fragment {
    RecyclerView recyclerView;
    Query query;
    FirebaseRecyclerOptions<Menu> options;
    FirebaseRecyclerAdapter<Menu, CategoryViewHolder> adapter;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.recycler_menu);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Category");

        options = new FirebaseRecyclerOptions.Builder<Menu>()
                .setQuery(query, Menu.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Menu, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Menu model) {
                holder.textView.setText(model.getName());
                Picasso.with(getContext()).load(model.getImage()).fit().into(holder.imageView);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), FoodListActivity.class);
                        intent.putExtra("CategoryID", adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
                CategoryViewHolder viewHolder = new CategoryViewHolder(view);
                return viewHolder;
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}