package com.example.maptyxf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CIndex extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cindex);


        // Set up the back button click listener
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the MapsActivity
                Intent intent = new Intent(CIndex.this, MapsActivity.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }

    // Override the onBackPressed method to handle the back button press
    @Override
    public void onBackPressed() {
        // Navigate back to the MapsActivity
        super.onBackPressed();
        Intent intent = new Intent(CIndex.this, MapsActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }

}