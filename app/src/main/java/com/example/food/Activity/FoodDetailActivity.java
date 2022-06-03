package com.example.food.Activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food.Database.Database;
import com.example.food.Model.Food;
import com.example.food.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity {

    private TextView btnAddToCart;
    private TextView txtTitle, txtFee, txtDiscount, txtNumberOrder;
    private ImageView btnPlus, btnMinus, imgFood;

    int numberOrder = 1;
    String foodID = null;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initView();
        if (getIntent() != null) {
            foodID = getIntent().getStringExtra("FoodID");
        }
        if (!foodID.isEmpty() && foodID != null)
            loadContent(foodID);
    }

    private void loadContent(String foodID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_food = database.getReference("Food");
        table_food.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                food = snapshot.child(foodID).getValue(Food.class);
                txtTitle.setText(food.getName());
                if (food.getDiscount() == 0) {
                    txtFee.setText(food.getPrice() + "VNĐ");
                } else {
                    int discount = food.getPrice() * food.getDiscount() / 100;
                    txtDiscount.setText(food.getPrice() + "VNĐ");
                    txtDiscount.setPaintFlags(txtDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    txtFee.setText(food.getPrice() - discount + "VNĐ");
                }
                Picasso.with(getBaseContext()).load(food.getImage()).into(imgFood);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        btnAddToCart = findViewById(R.id.addToCartBtn);
        txtTitle = findViewById(R.id.titleTxt);
        txtFee = findViewById(R.id.priceTxt);
        txtDiscount = findViewById(R.id.discountTxt);
        txtNumberOrder = findViewById(R.id.numberOrderTxt);
        btnPlus = findViewById(R.id.plusBtn);
        btnMinus = findViewById(R.id.minusBtn);
        imgFood = findViewById(R.id.picfood);

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                txtNumberOrder.setText(String.valueOf(numberOrder));
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                txtNumberOrder.setText(String.valueOf(numberOrder));
            }
        });


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sql = String.format("INSERT INTO OrderDetail " +
                                    "(ProductId, ProductName, Quantity, Price, Discount, Image)" +
                                    "VALUES ('%s','%s','%s','%s','%s','%s')",
                            foodID, food.getName(), numberOrder, food.getPrice(), food.getDiscount(), food.getImage());

                    new Database(getBaseContext()).QueryData(sql);

                    Toast.makeText(getBaseContext(), "Added to Cart", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getBaseContext(), "ERROR: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}