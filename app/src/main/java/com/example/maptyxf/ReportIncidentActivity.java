package com.example.maptyxf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class ReportIncidentActivity extends AppCompatActivity {

    EditText placeEditText,typeEditText,descriptionEditText;
    Button saveincidentbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);

        placeEditText = findViewById(R.id.incident_place_title);
        typeEditText = findViewById(R.id.incident_type_title);
        descriptionEditText = findViewById(R.id.description_content_text);
        saveincidentbtn = findViewById(R.id.incident_btn2);

        saveincidentbtn.setOnClickListener( (v)-> saveIncident());
    }

    void saveIncident(){
        String noteTitle = placeEditText.getText().toString();
        String notetype = typeEditText.getText().toString();
        String noteContent = descriptionEditText.getText().toString();
        if(noteTitle==null || noteTitle.isEmpty() ){
            placeEditText.setError("Title is required");
            return;
        }
        Incident incident = new Incident();
        incident.setIncidentPlace(noteTitle);
        incident.setType(notetype);
        incident.setDescription(noteContent);
        incident.setTimestamp(Timestamp.now());

        saveIncidentToFirebase(incident);
    }

    void saveIncidentToFirebase(Incident incident){

        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForIncident().document();

        documentReference.set(incident).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //note is added
                    Toast.makeText(ReportIncidentActivity.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ReportIncidentActivity.this, "Failed while adding Review", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

    }
}