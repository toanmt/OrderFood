package com.example.food.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food.Model.Food;
import com.example.food.R;
import com.example.food.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class FoodListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
    }

    private void loadList(String categoryID) {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Food")
                .orderByChild("menuID")
                .equalTo(categoryID);

        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(query, Food.class)
                        .build();
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {
                holder.textView.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(holder.imageView);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FoodListActivity.this, FoodDetailActivity.class);
                        intent.putExtra("FoodID", adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
                FoodViewHolder viewHolder = new FoodViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String categoryID = null;
        if (getIntent() != null) {
            categoryID = getIntent().getStringExtra("CategoryID");
        }
        if (!categoryID.isEmpty() && categoryID != null)
            loadList(categoryID);
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}