package com.example.maptyxf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button showmap;
    Button savereviewbutton;
    Button saveincidentbutton;
    Button details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        showmap = findViewById(R.id.showMapsButton);
        savereviewbutton = findViewById(R.id.showReview);
        saveincidentbutton = findViewById(R.id.saveIncident);
        details = findViewById(R.id.showIndexButton);

        showmap.setOnClickListener((v)-> startActivity(new Intent(DashboardActivity.this,MapsActivity.class)) );
        details.setOnClickListener((v)-> startActivity(new Intent(DashboardActivity.this,CIndex.class)) );
        savereviewbutton.setOnClickListener((v)-> startActivity(new Intent(DashboardActivity.this,ReviewDetailsActivity.class)) );
        saveincidentbutton.setOnClickListener((v)-> startActivity(new Intent(DashboardActivity.this,ReportIncidentActivity.class)) );
    }

}