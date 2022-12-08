package com.example.appindev;

import java.util.ArrayList;

public class Question {
    private String questionText;
    private ArrayList<Answer> answers;

    public Question(String text, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3){
        this.questionText = text;
        this.answers = new ArrayList<>();
        answers.add(new CorrectAnswer(correctAnswer));
        answers.add(new WrongAnswer(wrongAnswer1));
        answers.add(new WrongAnswer(wrongAnswer2));
        answers.add(new WrongAnswer(wrongAnswer3));
    }

    public ArrayList<Answer> getAnswers(){
        return this.answers;
    }
}
