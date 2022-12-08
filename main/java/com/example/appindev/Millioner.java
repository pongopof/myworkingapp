package com.example.appindev;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Millioner {
    private ArrayList<Question> questions;
    private Scene lastScene;
    private Stage stage;
    private BorderPane mainPane;
    private GridPane buttonsPane;
    private AButton A;
    private AButton B;
    private AButton C;
    private AButton D;
    private Question question;
    private int questionNumber;


    public Millioner() {
        mainPane = new BorderPane();
        buttonsPane = new GridPane();
        mainPane.setCenter(buttonsPane);
        this.questions = new ArrayList<Question>();




    }

    public void addQuestionBank(){
        this.questions.add(new Question("Pierwszy lek w leczeniu miażdzycy", "statyna" , "fibrat", "jakieś gówno", "cebula"));
        this.questions.add(new Question("Pierwszy lek w leczeniu elegible", "prawidłowa odp" , "zgd", "fg gówno", "cebula"));
    }

    public void nextQuestion(){

        Random random = new Random();
        question = this.questions.get(questionNumber);
        int r = random.nextInt(4);
        ArrayList<Answer> answers = question.getAnswers();
        System.out.println(answers);
        int numA = 0;
        int numB = 0;
        int numC = 0;
        int numD = 0;



        numA = random.nextInt(4);
        System.out.println(numA);

        numB = random.nextInt(4);
        while(numB == numA) {
            numB = random.nextInt(4);
        }
        System.out.println(numB);
        numC = random.nextInt(4);
        while(numC == numB || numC == numA) {
            numC = random.nextInt(4);
        }
        System.out.println(numC);

        numD = random.nextInt(4);
        while(numD == numB || numD == numA || numD == numC) {
            numD = random.nextInt(4);
        }
        System.out.println(numD);

        Answer buttonAnswer = answers.get(numA);
        this.A = new AButton(buttonAnswer, this);

        buttonAnswer = answers.get(numB);
        this.B = new AButton(buttonAnswer, this);

        buttonAnswer = answers.get(numC);
        this.C = new AButton(buttonAnswer, this);

        buttonAnswer = answers.get(numD);
        this.D = new AButton(buttonAnswer, this);

        buttonsPane.add(A, 0 , 0);
        buttonsPane.add(B, 0, 1);
        buttonsPane.add(C, 1, 0);
        buttonsPane.add(D, 1, 1);



    }

    public void givedAnswer(AButton button){
        if(button.getAnswer() instanceof CorrectAnswer){
            this.correctAnswer();
        }
    }

    public void correctAnswer(){
        questionNumber++;
    }

    public Scene getScene(){
        Scene millionerScene = new Scene(mainPane);
        this.addQuestionBank();
        this.nextQuestion();
        return millionerScene;
    }


}
