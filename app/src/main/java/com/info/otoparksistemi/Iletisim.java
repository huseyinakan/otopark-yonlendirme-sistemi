package com.info.otoparksistemi;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.info.otoparksistemi.databinding.ActivityIletisimBinding;

public class Iletisim extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityIletisimBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIletisimBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button iletisimToAnaSayfa = findViewById(R.id.iletisimToAnaSayfa);
        iletisimToAnaSayfa.setOnClickListener(v -> {
            Intent intent = new Intent(Iletisim.this,BaslangicSayfa.class);
            startActivity(intent);
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng serdivanOtopark = new LatLng(40.74235104158795, 30.325473800755045);
        mMap.addMarker(new MarkerOptions().position(serdivanOtopark).title("Serdivan Otopark"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(serdivanOtopark,16));
    }
}