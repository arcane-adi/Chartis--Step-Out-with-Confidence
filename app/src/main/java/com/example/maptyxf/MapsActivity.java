package com.example.maptyxf;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private EditText sourceLatitudeEditText;
    private Button enterButton;
    private Python python;
    private ArrayList<String> manualKeys = new ArrayList<>();
    private ArrayList<String> manualValues = new ArrayList<>();
    private ArrayList<String> manualPrecautions = new ArrayList<>();
    private ArrayList<String> manualPopulation = new ArrayList<>();
    private static final String CHANNEL_ID = "Danger_Channel";
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button logout;
    private Button b1;
    private Button showReview;
    private Button showCurrentLocationButton;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logoutButton);
        user = auth.getCurrentUser();
        b1 = findViewById(R.id.showIndexButton);
        showReview = findViewById(R.id.showReview);
        showCurrentLocationButton = findViewById(R.id.showCurrentLocationButton);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        showReview.setOnClickListener(v -> startActivity(new Intent(MapsActivity.this, ReviewDetailsActivity.class)));

        b1.setOnClickListener(v -> {
            Intent intent = new Intent(MapsActivity.this, CIndex.class);
            startActivity(intent);
            finish();
        });

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        manualKeys.add("swargate");
        manualKeys.add("Pune railway station");
        manualKeys.add("bhimashankar");
        manualKeys.add("talegaon");
        manualKeys.add("chakan");
        manualKeys.add("katraj");
        manualKeys.add("dehu");
        manualKeys.add("baner");
        manualKeys.add("kothrud");
        manualKeys.add("Bhugaon");
        manualKeys.add("Wadgaon Sheri");
        manualKeys.add("Lonavala");
        manualKeys.add("Khadki");
        manualKeys.add("Dighi");
        manualKeys.add("Alandi");
        manualKeys.add("Jejuri");
        manualKeys.add("Shirur");
        manualKeys.add("Daund");
        manualKeys.add("Baramati");
        manualKeys.add("Indapur");
        manualKeys.add("Vadgaon");
        manualKeys.add("Mulshi");
        manualKeys.add("Lavasa");
        manualKeys.add("Panshet");
        manualKeys.add("Loni Kalbhor");
        manualKeys.add("Wagholi");
        manualKeys.add("Uruli Kanchan");
        manualKeys.add("Yewalewadi");
        manualKeys.add("Kondhwa");
        manualKeys.add("Wadgaon Maval");


        manualValues.add("theft");
        manualValues.add("crowded");
        manualValues.add("animal trespassing");
        manualValues.add("animal trespassing");
        manualValues.add("crowded");
        manualValues.add("theft");
        manualValues.add("accidents");
        manualValues.add("criminal incidents");
        manualValues.add("criminal incidents");
        manualValues.add("Animal trespassing");
        manualValues.add("Theft");
        manualValues.add("Natural disasters (landslides)");
        manualValues.add("Accidents (drowning)");
        manualValues.add("Poor street lighting");
        manualValues.add("Traffic congestion");
        manualValues.add("Isolated areas");
        manualValues.add("Poor street lighting");
        manualValues.add("Theft");
        manualValues.add("Natural disasters (floods)");
        manualValues.add("Poor street lighting");
        manualValues.add("Animal trespassing");
        manualValues.add("Natural disasters (landslides)");
        manualValues.add("Accidents (drowning)");
        manualValues.add("Poor street lighting");
        manualValues.add("Isolated areas");
        manualValues.add("Poor street lighting");
        manualValues.add("Theft");
        manualValues.add("Natural disasters (floods)");
        manualValues.add("Animal trespassing");
        manualValues.add("Theft");
        manualValues.add("Natural disasters (landslides)");

        manualPrecautions.add("Keep valuables secure, avoid walking alone at night");
        manualPrecautions.add("Keep valuables secure, avoid walking alone at night");
        manualPrecautions.add("uneven terrain, Follow guide instructions, wear proper footwear");
        manualPrecautions.add("water pollution, Avoid swimming, keep the area clean");
        manualPrecautions.add("stray dogs, Carry a flashlight, avoid walking alone at night");
        manualPrecautions.add("construction hazards, Follow traffic rules, avoid construction sites");
        manualPrecautions.add("poor security, Avoid walking alone, use secure parking");
        manualPrecautions.add("stray animals, Carry a flashlight, avoid walking alone at night");
        manualPrecautions.add("industrial hazards, Keep valuables secure, avoid industrial areas");
        manualPrecautions.add("poor lighting, Avoid walking alone at night, use streetlights");
        manualPrecautions.add("Keep valuables secure, avoid walking alone at night");
        manualPrecautions.add("uneven terrain, Follow guide instructions, wear proper footwear");
        manualPrecautions.add("water pollution, Avoid swimming, keep the area clean");
        manualPrecautions.add("stray dogs, Carry a flashlight, avoid walking alone at night");
        manualPrecautions.add("construction hazards, Follow traffic rules, avoid construction sites");
        manualPrecautions.add("poor security, Avoid walking alone, use secure parking");
        manualPrecautions.add("stray animals, Carry a flashlight, avoid walking alone at night");
        manualPrecautions.add("industrial hazards, Keep valuables secure, avoid industrial areas");
        manualPrecautions.add("Follow evacuation routes, keep emergency numbers handy");
        manualPrecautions.add("isolated areas, Avoid walking alone at night, use streetlights");
        manualPrecautions.add("poor lighting, Avoid walking alone at night, use streetlights");
        manualPrecautions.add("uneven terrain, Follow guide instructions, wear proper footwear");
        manualPrecautions.add("water pollution, Avoid swimming, keep the area clean");
        manualPrecautions.add("stray dogs, Carry a flashlight, avoid walking alone at night");
        manualPrecautions.add("poor security, Avoid walking alone, use secure parking");
        manualPrecautions.add("stray animals, Carry a flashlight, avoid walking alone at night");
        manualPrecautions.add("industrial hazards, Keep valuables secure, avoid industrial areas");
        manualPrecautions.add("Follow evacuation routes, keep emergency numbers handy");
        manualPrecautions.add("poor lighting, Avoid walking alone at night, use streetlights");
        manualPrecautions.add("overcrowding, Keep valuables secure, avoid walking alone at night");

        manualPopulation.add("6485");
        manualPopulation.add("3.9 lakhs");
        manualPopulation.add("81,000");
        manualPopulation.add("66727");
        manualPopulation.add("38483");
        manualPopulation.add("28,576");
        manualPopulation.add("12,000");
        manualPopulation.add("37,111");
        manualPopulation.add("67,000");
        manualPopulation.add("5,949");
        manualPopulation.add("3.9 lakh");
        manualPopulation.add("81,000");
        manualPopulation.add("66,727");
        manualPopulation.add("38,483");
        manualPopulation.add("28,576");
        manualPopulation.add("12,000");
        manualPopulation.add("50,000");
        manualPopulation.add("3,80,496");
        manualPopulation.add("76,000");
        manualPopulation.add("25,515");
        manualPopulation.add("11,359");
        manualPopulation.add("1,71,006");
        manualPopulation.add("1932");
        manualPopulation.add("1,570");
        manualPopulation.add("22,518");
        manualPopulation.add("1,04,584");
        manualPopulation.add("30,305");
        manualPopulation.add("10,700");
        manualPopulation.add("1,32,843");
        manualPopulation.add("11,359");

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        python = Python.getInstance();

        sourceLatitudeEditText = findViewById(R.id.sourceLatitudeEditText);
        enterButton = findViewById(R.id.enterButton);

        sourceLatitudeEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String address = sourceLatitudeEditText.getText().toString();
                moveToLocation(address);
                return true;
            }
            return false;
        });

        enterButton.setOnClickListener(view -> {
            String address = sourceLatitudeEditText.getText().toString();
            moveToLocation(address);
            addPredefinedCircles();
        });

        showCurrentLocationButton.setOnClickListener(view -> showCurrentLocation());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MapsActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng defaultLocation = new LatLng(18.516726, 73.856255);
        mMap.addMarker(new MarkerOptions().position(defaultLocation).title("Marker in Default Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 13f));

        addPredefinedCircles();

        mMap.setOnMarkerClickListener(marker -> {
            showPopup(marker.getTitle());
            return true;
        });
    }

    private void addPredefinedCircles() {
        addCircle(new LatLng(18.5018, 73.8636), "Swargate", "theft", Color.argb(125, 165, 42, 42));
        addCircle(new LatLng(19.0720, 73.5357), "Bhimashankar", "animal trespassing", Color.argb(125, 255, 255, 0));
        addCircle(new LatLng(18.7376, 73.6747), "Talegaon", "animal trespassing", Color.argb(125, 255, 255, 0));
        addCircle(new LatLng(18.5590, 73.7768), "Baner", "stray animals", Color.argb(125, 255, 0, 0));
        addCircle(new LatLng(18.5074, 73.8077), "Kothrud", "criminal incidents", Color.argb(125, 255, 0, 0));
        addCircle(new LatLng(18.7167, 73.7678), "Dehu", "accidents", Color.argb(125, 0, 0, 139));
        addCircle(new LatLng(18.7632, 73.8613), "Chakan", "crowded", Color.argb(125, 135, 206, 235));
        addCircle(new LatLng(18.4529, 73.8652), "Katraj", "theft", Color.argb(125, 165, 42, 42));
        addCircle(new LatLng(18.4621, 74.5840), "Daund", "theft", Color.argb(125, 165, 42, 42)); // Brown
        addCircle(new LatLng(18.5989, 73.7373), "Manchar", "accidents", Color.argb(125, 128, 0, 128)); // Purple
        addCircle(new LatLng(18.465, 73.8508), "Parvati", "theft", Color.argb(125, 165, 42, 42)); // Brown
        addCircle(new LatLng(18.5707, 73.884), "Panshet", "poor street lighting", Color.argb(125, 169, 169, 169)); // Grey
        addCircle(new LatLng(18.6411, 73.9893), "Yewalewadi", "natural disasters (floods)", Color.argb(125, 0, 100, 0)); // Dark Green
        addCircle(new LatLng(18.4539, 74.2188), "Chakan", "accidents (drowning)", Color.argb(125, 0, 0, 255)); // Sky Blue
        addCircle(new LatLng(18.8192, 74.0173), "Uruli Kanchan", "theft", Color.argb(125, 165, 42, 42)); // Brown
        addCircle(new LatLng(18.5277, 73.8997), "Alandi", "traffic congestion", Color.argb(125, 135, 206, 235)); // Light Blue
        addCircle(new LatLng(18.4674, 73.8542), "Sahakar Nagar", "natural disasters (floods)", Color.argb(125, 0, 100, 0)); // Dark Green
        addCircle(new LatLng(18.5962, 74.2599), "Kondhwa", "animal trespassing", Color.argb(125, 144, 238, 144)); // Light Green
        addCircle(new LatLng(18.479, 73.8578), "Somatne", "theft", Color.argb(125, 165, 42, 42)); // Brown
    }

    private void addCircle(LatLng center, String title, String danger, int color) {
        mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(500)
                .fillColor(color));
        mMap.addMarker(new MarkerOptions().position(center).title(title));
    }

    private void showPopup(String location) {
        int index = manualKeys.indexOf(location.toLowerCase());
        if (index != -1) {
            String danger = manualValues.get(index);
            String precaution = manualPrecautions.get(index);
            String population = manualPopulation.get(index);

            new AlertDialog.Builder(this)
                    .setTitle(location)
                    .setMessage("Danger: " + danger + "\n" +
                            "Precaution: " + precaution + "\n" +
                            "Population: " + population)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private void moveToLocation(String address) {
        PyObject myModule = python.getModule("Script");
        PyObject addressToCoordinates = myModule.get("address_to_coordinates");

        String addressLowerCase = address.toLowerCase();
        int index = manualKeys.indexOf(addressLowerCase);

        if (index != -1) {
            String danger = manualValues.get(index);
            Toast.makeText(this, "Danger: " + danger, Toast.LENGTH_SHORT).show();
            sendNotification(danger);
        }

        PyObject result = addressToCoordinates.call(address);
        if (result != null && result.asList() != null && result.asList().size() == 4) {
            List<PyObject> resultList = result.asList();
            double latitude = resultList.get(1).toDouble();
            double longitude = resultList.get(3).toDouble();

            LatLng location = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14f));
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(location).title("Location"));
        } else {
            Toast.makeText(this, "Error fetching location", Toast.LENGTH_SHORT).show();
        }
    }

    private void showCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f));
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("My Location"));
            } else {
                Toast.makeText(MapsActivity.this, "Unable to get current location", Toast.LENGTH_SHORT).show();
            }
        });

        // Request new location if the last location is null or outdated
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // 10 seconds
        locationRequest.setFastestInterval(5000); // 5 seconds

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f));
                        mMap.addMarker(new MarkerOptions().position(currentLocation).title("My Location"));
                    }
                }
                fusedLocationClient.removeLocationUpdates(this);
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showCurrentLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendNotification(String danger) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Danger Channel";
            String description = "Channel for Danger Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Danger Alert")
                    .setContentText("Danger: " + danger)
                    .setPriority(Notification.PRIORITY_DEFAULT);
        }

        notificationManager.notify(1, builder.build());
    }
}
