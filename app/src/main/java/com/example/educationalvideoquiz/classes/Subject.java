package com.example.educationalvideoquiz.classes;

import java.util.List;

public class Subject {
    private String id;
    private String name;
    private List<StageLevel> levels;

    public Subject(String id, String name, List<StageLevel> levels) {
        this.id = id;
        this.name = name;
        this.levels = levels;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StageLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<StageLevel> levels) {
        this.levels = levels;
    }
}
