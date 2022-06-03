package com.example.food.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.food.Database.Database;
import com.example.food.R;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        Database database = new Database(this);
        database.QueryData("CREATE TABLE IF NOT EXISTS OrderDetail" +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ProductId TEXT," +
                "ProductName TEXT," +
                "Quantity TEXT," +
                "Price  TEXT," +
                "Discount TEXT," +
                "Image TEXT)");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);            }
        });
    }
}