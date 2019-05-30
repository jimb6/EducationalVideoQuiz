package com.example.educationalvideoquiz.classes;

import java.util.List;

public class StageLevel {
    private String id;
    private int levelNumber;
    private String videoUrl;
    private List<Question> questions;

    public StageLevel(String id, int levelNumber, String videoUrl, List<Question> questions) {
        this.id = id;
        this.levelNumber = levelNumber;
        this.videoUrl = videoUrl;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
