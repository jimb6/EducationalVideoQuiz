package com.example.educationalvideoquiz.classes;

import android.support.annotation.NonNull;

public class Score implements Comparable {
    private int score;
    private String playerName;

    public Score(String playerName,int score) {
        this.score = score;
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int compareTo(@NonNull Object score) {
        int comparescore=((Score)score).getScore();
        /* For Ascending order*/
        return comparescore-this.score;
    }
}
