package com.example.appindev;

public class CorrectAnswer implements Answer{

    private String answerText;

    public CorrectAnswer(String text){
        this.answerText = text;
    }

    public String getAnswerText(){
        return this.answerText;
    }


}
