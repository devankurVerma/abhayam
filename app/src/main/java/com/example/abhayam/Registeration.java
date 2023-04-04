package com.example.abhayam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registeration extends AppCompatActivity {
    EditText regiName , regiusername , regiParentPhNo , regiPassword;
    TextView loginRedirectText;
    Button registerNow;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        regiName = findViewById(R.id.regi_Name);
        regiusername = findViewById(R.id.regi_Email);
        regiParentPhNo = findViewById(R.id.regi_PhoneNumber);
        regiPassword = findViewById(R.id.regi_Password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        registerNow = findViewById(R.id.RegiButton);


        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = regiName.getText().toString();
                String username = regiusername.getText().toString();
                String ParentPhNo = regiParentPhNo.getText().toString();
                String Password = regiPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name,username,Password,ParentPhNo);
                reference.child(ParentPhNo).setValue(helperClass);

                Toast.makeText(Registeration.this, "your registration is completed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registeration.this,SigningIn.class);
                startActivity(intent);

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registeration.this,SigningIn.class);
                startActivity(intent);
            }
        });
    }
}