package com.example.maptyxf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class ReviewDetailsActivity extends AppCompatActivity {

    EditText titleEditText,contentEditText;
    Button saveNotebtn;
    Button incident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNotebtn = findViewById(R.id.save_note_btn);
        incident = findViewById(R.id.incident_btn);


        incident.setOnClickListener((v)-> startActivity(new Intent(ReviewDetailsActivity.this,ReportIncidentActivity.class)) );
        saveNotebtn.setOnClickListener( (v)-> saveNote());
    }
    void saveNote(){
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if(noteTitle==null || noteTitle.isEmpty() ){
            titleEditText.setError("Title is required");
            return;
        }
        Note note = new Note();
        note.setplace(noteTitle);
        note.setReview(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);

    }
    void saveNoteToFirebase(Note note) {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //note is added
                    Toast.makeText(ReviewDetailsActivity.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ReviewDetailsActivity.this, "Failed while adding Review", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }
}