package com.example.appindev;

public class WrongAnswer implements Answer{

    private String answerText;
    public WrongAnswer(String text){
        this.answerText = text;
    }

    public String getAnswerText(){
        return this.answerText;
    }
}
