package com.example.educationalvideoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.educationalvideoquiz.classes.Question;
import com.example.educationalvideoquiz.customadapters.QuestionListAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.firestore.FirebaseFirestore;

public class QuizActivity extends YouTubeBaseActivity {

    private static final String TAG = "QuizActivity";

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener ytOnInitializedListener;

    Button buttonPlay;
    ListView questionsListView;
    QuestionListAdapter questionListAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"onCreate: Starting.");
        //Setup controls
        buttonPlay = findViewById(R.id.buttonPlayVideo);
        youTubePlayerView = findViewById(R.id.youtubePlayer);
        questionsListView = findViewById(R.id.listviewQuestions);

        questionListAdapter = new QuestionListAdapter(this,new Question[]{});

        initYoutubeViewer();
        initQuestions();
    }
    private void initQuestions(){
    }

    private void initYoutubeViewer(){
        ytOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Done initializing.");
                youTubePlayer.loadVideo("7vKHjmOMNus");
                youTubePlayer.setFullscreen(false);
                youTubePlayer.setShowFullscreenButton(false);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Fail to initialize.");
            }
        };

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Initializing YouTube Player.");
                youTubePlayerView.initialize(YoutubeConfig.getApiKey(),ytOnInitializedListener);
            }
        });
    }
}
