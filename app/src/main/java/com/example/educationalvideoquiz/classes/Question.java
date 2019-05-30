package com.example.educationalvideoquiz.classes;

public class Question {

    private String id;
    private String description;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String answer;

    public Question(String id, String description, String choiceA, String choiceB, String choiceC, String answer) {
        this.id = id;
        this.description = description;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.answer = answer;
    }

    public Question(String id){
        this.id = id;
        this.description = "";
        this.answer = "";
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }
}
