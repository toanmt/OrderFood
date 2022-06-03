package com.example.food.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.Common.Common;
import com.example.food.Model.User;
import com.example.food.R;
import com.example.food.Util.Reusable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText edtPhone, edtPass;
    Button btnSignIn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtPhone = findViewById(R.id.edtPhoneNumber);
        edtPass = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignInActive);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reusable.showWaitingDialog(SignInActivity.this);
                if(edtPhone.getText().toString().equals("")||edtPass.getText().toString().equals("")){
                    Toast.makeText(SignInActivity.this,"Phone and Password is not blank!", Toast.LENGTH_LONG).show();
                    Reusable.dismissDialog();
                } else {
                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(edtPhone.getText().toString()).exists()) {
                                User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                if (user.getPassword().equals(edtPass.getText().toString())) {
                                    Reusable.dismissDialog();
                                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                    Common.user = user;
                                    startActivity(intent);
                                } else {
                                    Reusable.dismissDialog();
                                    Toast.makeText(SignInActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Reusable.dismissDialog();
                                Toast.makeText(SignInActivity.this, "Phone number is already exists!", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}