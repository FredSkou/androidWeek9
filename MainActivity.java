package com.example.week9firebasenote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public void saveNoteToFireBase(View view){
        Button saveNote = findViewById(R.id.saveNoteButton);

        saveFiles();

        Intent saveNoteIntent = new Intent(this,MainActivity.class);
        startActivity(saveNoteIntent);
    }

    public void saveFiles (){

        EditText noteText = findViewById(R.id.noteTextField);
        String noteTextString = noteText.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String collectionPath = "projects";

        Map<String, Object> data = new HashMap<>();
        data.put("note",noteTextString);
        db.collection(collectionPath)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("Data added successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Error while adding the data : " + e.getMessage());
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
