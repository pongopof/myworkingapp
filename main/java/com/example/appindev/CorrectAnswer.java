package com.example.appindev;

import java.io.Serializable;

public class CorrectAnswer implements Serializable, Answer  {

    private String answerText;

    public CorrectAnswer(String text){
        this.answerText = text;
    }

    public String getAnswerText(){
        return this.answerText;
    }

    @Override
    public String toString(){
        return this.answerText;
    }


}
