package com.example.appindev;

import java.io.Serializable;

public class WrongAnswer implements Answer, Serializable {

    private String answerText;
    public WrongAnswer(String text){
        this.answerText = text;
    }

    public String getAnswerText(){
        return this.answerText;
    }


    @Override
    public String toString(){
        return this.answerText;
    }

    public void setAnswer(String string){
        this.answerText = string;
    }
}


