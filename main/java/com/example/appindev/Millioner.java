package com.example.appindev;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Millioner implements Serializable {
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

    private BorderPane questionPane;
    private Label topQuizLabel;

    private Button nextQuestionButton;

    private int points;
    private Label pointsLabel;



    public Millioner(Stage stage, String questionFilePath) {
        this.addQuestionBank();
        this.stage = stage;
        mainPane = new BorderPane();
        questionPane = new BorderPane();
        mainPane.setCenter(questionPane);
        this.points = 0;


        this.topQuizLabel = new Label();



        mainPane.setTop(this.topQuizLabel);
        this.questions = new ArrayList<Question>();






    }



    public void addQuestionBank(){
        this.questions = new ArrayList<>();
        ArrayList<Question> myList = new ArrayList();
        ObjectInputStream oi = null;
        try {
            FileInputStream fi = new FileInputStream("C:\\Users\\shand\\IdeaProjects\\myworkin\\questions.txt");
            oi = new ObjectInputStream(fi);
            ArrayList<Question> readCase = (ArrayList<Question>) oi.readObject();
            myList = readCase;
            oi.close();
        } catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e ) {
            System.out.println("ClassNotFoundException");
        }

        QuestionsBank qBank = new QuestionsBank();
        this.questions.addAll(myList);
        this.questions.addAll(qBank.getQuestions());

    }

    public void nextQuestion(){


        this.topQuizLabel.setText("Answer the question");
        Random random = new Random();
        question = this.questions.get(questionNumber);
        int r = random.nextInt(4);

        question.getQuestionField().initialize(this);
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

        buttonsPane = new GridPane();
        questionPane.setCenter(buttonsPane);

        buttonsPane.add(A, 0 , 0);
        buttonsPane.add(B, 0, 1);
        buttonsPane.add(C, 1, 0);
        buttonsPane.add(D, 1, 1);



    }

    private void plusPoint(){
        this.points++;
        this.pointsLabel = new Label("Points: " + this.points);
        questionPane.setBottom(pointsLabel);
    }

    public void givedAnswer(AButton button){
        if(button.getAnswer() instanceof CorrectAnswer){
            this.correctAnswer();
        } else {
            this.wrongAnswer();
        }
        this.disableButtons();
    }

    public void disableButtons(){
        this.A.setDisable(true);
        this.B.setDisable(true);
        this.C.setDisable(true);
        this.D.setDisable(true);
    }

    public BorderPane getQuestionPane(){
        return this.questionPane;
    }

    public void correctAnswer(){

        this.plusPoint();
        this.topQuizLabel.setText("Correct Answer!");
        questionNumber++;


        if(questionNumber < this.questions.size()) {
            this.nextQuestionButton = new Button("Next");
            this.nextQuestionButton.setOnAction((event) ->{
                this.nextQuestion();
            });
            this.buttonsPane.add(this.nextQuestionButton, 1, 2);

        } else {
            this.finalVictory();
        }
    }

    public void finalVictory(){
        Stage victoryPopup = new Stage();
        victoryPopup.initModality(Modality.APPLICATION_MODAL);
        victoryPopup.setTitle("vicotry");
        Label victoryLabel = new Label("you won million!!!");
        Button exitVictoryButton = new Button("Exit");
        exitVictoryButton.setOnAction(e -> victoryPopup.close());
        VBox layout = new VBox();
        layout.getChildren().addAll(victoryLabel, exitVictoryButton);
        Scene sceneVictory = new Scene(layout);
        victoryPopup.setScene(sceneVictory);
        victoryPopup.showAndWait();
        this.gameEnd();
    }



    public void wrongAnswer(){

        Stage popupWrongAnaswer = new Stage();
        popupWrongAnaswer.initModality(Modality.APPLICATION_MODAL);
        popupWrongAnaswer.setTitle("Wrong Answer");

        Label wrongLabel = new Label("Wrong Answer!");

        Button exitWrongButton = new Button("Exit");
        exitWrongButton.setOnAction(e -> popupWrongAnaswer.close());


        VBox layout = new VBox();
        layout.getChildren().addAll(wrongLabel, exitWrongButton);
        Scene sceneWrong = new Scene(layout);

        popupWrongAnaswer.setScene(sceneWrong);

        popupWrongAnaswer.showAndWait();
        this.gameEnd();


    }

    public void gameEnd(){
        this.stage.setScene(this.lastScene);

    }

    public Scene getScene(Scene scene){
        this.lastScene = scene;
        Scene millionerScene = new Scene(mainPane, 320, 240);

        //QuestionsBank bank = new QuestionsBank();
       // this.questions = bank.getQuestions();



        this.addQuestionBank();
        this.nextQuestion();
        return millionerScene;
    }


}
