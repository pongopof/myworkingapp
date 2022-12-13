package com.example.appindev;

import java.util.ArrayList;

public class Question {
    private Questionable questionField;
    private ArrayList<Answer> answers;

    public Question(String text, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3){
        this.questionField = new QuestionText(text);
        this.answers = new ArrayList<>();
        answers.add(new CorrectAnswer(correctAnswer));
        answers.add(new WrongAnswer(wrongAnswer1));
        answers.add(new WrongAnswer(wrongAnswer2));
        answers.add(new WrongAnswer(wrongAnswer3));
    }

    public Question(String questionText, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String filePath){
        this.answers = new ArrayList<>();
        answers.add(new CorrectAnswer(correctAnswer));
        answers.add(new WrongAnswer(wrongAnswer1));
        answers.add(new WrongAnswer(wrongAnswer2));
        answers.add(new WrongAnswer(wrongAnswer3));
        this.questionField = new ImageQuestion(filePath, questionText);
    }

    public Questionable getQuestionField(){
        return this.questionField;
    }

    public ArrayList<Answer> getAnswers(){
        return this.answers;
    }
}
