package com.example.abhayam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiLoginSection extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_login_section);

        Button student = findViewById(R.id.userStudent);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLoginSection.this,Registeration.class);
                startActivity(intent);
            }
        });
        Button parent = findViewById(R.id.userParent);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLoginSection.this,GuardianSection.class);
                startActivity(intent);
            }
        });

    }
}