package com.example.educationalvideoquiz;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.educationalvideoquiz.classes.Score;
import com.example.educationalvideoquiz.customadapters.ScoreListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    ListView listView;
    Button buttonBack;
    ScoreListAdapter leaderboardListAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Score Leaderboard");
        setContentView(R.layout.activity_leaderboard);

        buttonBack = findViewById(R.id.buttonBack);
        listView = findViewById(R.id.listView);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        leaderboardListAdapter = new ScoreListAdapter(this,new int[]{},new String[]{});
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Fetching leaderboard...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        db.collection("leaderboard")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Score> leaderboardList = new ArrayList<>();

                        if (task.isSuccessful()) {
                            leaderboardListAdapter.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int ss = Integer.parseInt(document.get("score").toString());
                                leaderboardList.add(new Score(document.get("name").toString(),ss));
                                Log.d("Normal", document.getId() + " => " + document.getData());
                            }

                            Collections.sort(leaderboardList);
                            int[] scores = new int[leaderboardList.size()];
                            String[] names = new String[leaderboardList.size()];
                            for(int i = 0;i< leaderboardList.size();i++){
                                scores[i] = leaderboardList.get(i).getScore();
                                names[i] = leaderboardList.get(i).getPlayerName();
                            }
                            setList(names,scores);
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                            Toast.makeText(getApplicationContext(),"Error getting the scores.",Toast.LENGTH_LONG).show();
                        }

                        progress.dismiss();
                    }
                });



    }

    public void setList(String[]names,int [] scores){
        leaderboardListAdapter = new ScoreListAdapter(this,scores,names);
        listView.setAdapter(leaderboardListAdapter);
    }
    public void goBack(){
        finish();
    }
}
