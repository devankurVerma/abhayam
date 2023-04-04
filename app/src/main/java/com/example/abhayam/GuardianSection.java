package com.example.abhayam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GuardianSection extends AppCompatActivity {

    EditText guarPhoneNumber;
    Button guarSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_section);

        guarPhoneNumber = findViewById(R.id.guarParentPhNo);
        guarSubmit = findViewById(R.id.guardSubmit);

        guarSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = guarPhoneNumber.getText().toString();

                Intent intent = new Intent(getApplicationContext(),HomePageGard.class);
                intent.putExtra("projectMadeByAnkur", message);
                startActivity(intent);

            }
        });


    }

//AIzaSyCOhUlOzGlQEF53WHYgwP1QhAhtLjsXmdM

}