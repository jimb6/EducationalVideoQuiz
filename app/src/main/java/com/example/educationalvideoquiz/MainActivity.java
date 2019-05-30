package com.example.educationalvideoquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationalvideoquiz.classes.Question;
import com.example.educationalvideoquiz.classes.Subject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner subjectSpinner;
    Button playButton;
    Button leaderboardButton;
    TextView nameTextView;

    ArrayAdapter<String> subjectsAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.buttonPlay);
        nameTextView = findViewById(R.id.textUsername);
        leaderboardButton = findViewById(R.id.buttonLeader);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameTextView.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter your name first.",Toast.LENGTH_LONG).show();
                }else{
                    startQuiz(v);
                }
            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeaderboard(v);
            }
        });

        subjectSpinner = findViewById(R.id.spinnerSubject);
        subjectsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,new ArrayList<String>());
        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching subjects...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        db.collection("subject")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        subjectsAdapter.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Normal", document.getId() + " => " + document.getData());
                                subjectsAdapter.add(document.get("name").toString());
                            }
                            subjectSpinner.setAdapter(subjectsAdapter);
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                            Toast.makeText(getApplicationContext(),"Error getting the subjects.",Toast.LENGTH_LONG).show();
                        }
                        progress.dismiss();
                    }
                });

        db.collection("test").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

            }
        });
    }

    public void showLeaderboard(View view){
        Intent intent = new Intent(this,LeaderboardActivity.class);
        startActivity(intent);
    }

    public void startQuiz(View view){
        Intent intent = new Intent(this,QuizActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
}
