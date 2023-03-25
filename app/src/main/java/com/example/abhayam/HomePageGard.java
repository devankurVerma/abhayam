package com.example.abhayam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomePageGard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_gard);
        TextView textView = findViewById(R.id.dataStudent1);
        textView.setText(getIntent().getExtras().getString("Message"));
    }
}