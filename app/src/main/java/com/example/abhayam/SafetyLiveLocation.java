package com.example.abhayam;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.abhayam.databinding.ActivitySafetyLiveLocationBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SafetyLiveLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySafetyLiveLocationBinding binding;

    public String triggerPhNo;
    public String triggerLatitude;
    public String triggerLongitude;

    public Double Lat;
    public Double Longi;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        Intent isActivity = getIntent();
//        triggerPhNo = isActivity.getStringExtra("thisIsPassingValueBtwHmASl");

        reciveDataLocation();

        binding = ActivitySafetyLiveLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        Intent isActivity = getIntent();
//        triggerPhNo = isActivity.getStringExtra("thisIsPassingValueBtwHmASl");
        Lat = Double.parseDouble(triggerLatitude);
        Longi = Double.parseDouble(triggerLongitude);
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Lat,Longi);
        mMap.addMarker(new MarkerOptions().position(sydney).title("SOS location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void reciveDataLocation(){

        Intent isActivity = getIntent();
        triggerPhNo = isActivity.getStringExtra("thisIsPassingValueBtwHmASl");
        reference.child("users").child(triggerPhNo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                triggerLatitude = snapshot.child("latitude").getValue(String.class);
                triggerLongitude = snapshot.child("longitude").getValue(String.class);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SafetyLiveLocation.this, "your data is not fetched", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

//reference.child("users").child(fetchNumb).addValueEventListener
//String name = snapshot.child("name").getValue(String.class);
//                chName.setText(name);