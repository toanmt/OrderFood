package com.example.food.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food.Model.User;
import com.example.food.R;
import com.example.food.Util.Reusable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText edtName, edtPhone, edtPass;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhoneNumber);
        edtPass = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUpActive);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference ("User");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reusable.showWaitingDialog(SignUpActivity.this);
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(edtPhone.getText().toString()).exists()) {
                            Reusable.dismissDialog();
                            Toast.makeText(SignUpActivity.this, "Phone number is exists!", Toast.LENGTH_LONG).show();
                        } else {
                            Reusable.dismissDialog();
                            User user = new User(edtName.getText().toString(), edtPass.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}