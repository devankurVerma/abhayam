package com.example.abhayam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePageGard extends AppCompatActivity {

    public String fetchNumb;
    TextView chName, chLatitude, chLongitude;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_gard);

        chName = findViewById(R.id.NameOfStudent);
        chLatitude = findViewById(R.id.latitudeOfStudent);
        chLongitude = findViewById(R.id.longitudeOfStudent);

        Intent ofActivity = getIntent();
        fetchNumb = ofActivity.getStringExtra("projectMadeByAnkur");

        Intent intent = new Intent(HomePageGard.this, SafetyLiveLocation.class);
        intent.putExtra("thisIsLocationValue",fetchNumb );

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        readingData();

    }

    public void readingData(){
        reference.child("users").child(fetchNumb).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                chName.setText(name);

                String latitudeChild = snapshot.child("latitude").getValue(String.class);
                chLatitude.setText(latitudeChild);

                String longitudeChild = snapshot.child("longitude").getValue(String.class);
                chLongitude.setText(longitudeChild);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageGard.this, "data not fetched properly", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//    getReference().child(DATABASE_NODE_NAME).child(ret_id).addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        String ret_name=dataSnapshot.child("name").getValue().toString();//retrieved name
//        }