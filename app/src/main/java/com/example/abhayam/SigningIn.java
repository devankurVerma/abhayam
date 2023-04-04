package com.example.abhayam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SigningIn extends AppCompatActivity {

    EditText logiEmail , logiPassword ,sosNumb;
    Button loginNow;
    TextView regiRedirectText;

    public String mobiNumberParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing_in);

        logiEmail = findViewById(R.id.logi_Email);
        logiPassword = findViewById(R.id.logi_Password);
        regiRedirectText = findViewById(R.id.RegiRedirectText);
        loginNow = findViewById(R.id.logiButton);
        sosNumb = findViewById(R.id.logi_sosNumbGuard);





        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validationEmail() | !validationPassword()){

                }else {
                    checkUser();
                }
            }
        });
        regiRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigningIn.this,Registeration.class);
                startActivity(intent);
            }
        });


    }

    public boolean validationEmail(){
        String val = logiEmail.getText().toString();
        if(val.isEmpty()){

            logiEmail.setError("fill details");
            return false;

        }else {

            logiEmail.setError(null);
            return true;
        }
    }

    public boolean validationPassword(){
        String val = logiPassword.getText().toString();
        if(val.isEmpty()){

            logiPassword.setError("fill details");
            return false;

        }else {

            logiPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String UserEmail = logiEmail.getText().toString().trim();
        String UserPassword = logiPassword.getText().toString().trim();
        mobiNumberParent = sosNumb.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(UserEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    logiEmail.setError(null);
                    String passwordFromDb = snapshot.child(UserEmail).child("Password").getValue(String.class);

                    if (!Objects.equals(passwordFromDb,UserPassword)){
                        logiEmail.setError(null);
                        Intent intent = new Intent(SigningIn.this,MainActivity.class);

                        intent.putExtra("Aumkar", mobiNumberParent);

                        startActivity(intent);
                    }else {
                        logiPassword.setError("invalid credentials");
                        logiPassword.requestFocus();
                    }
                }else {
                    logiEmail.setError("user not exist");
                    logiEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}