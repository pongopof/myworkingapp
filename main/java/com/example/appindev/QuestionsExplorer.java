package com.example.appindev;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class QuestionsExplorer implements Serializable{
    private Stage stage;
    private Scene lastScene;
    private BorderPane mainLayout;
    private VBox questionsListedVBox;
    private ArrayList<Question> questions;

    private Accordion accordion;


    public QuestionsExplorer(Stage stage){
        this.stage = stage;
        mainLayout = new BorderPane();
        questionsListedVBox = new VBox();
        this.questions = new ArrayList();
        this.accordion = new Accordion();
        mainLayout.setCenter(accordion);
        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            this.stage.setScene(lastScene);
        });
        mainLayout.setBottom(returnButton);
    }



    public void getQuestions(){
        ArrayList<Question> myList = new ArrayList();
        ObjectInputStream oi = null;
        try {
            FileInputStream fi = new FileInputStream("C:\\Users\\piotr\\eclipse-workspace\\AppInDev\\src\\questions.txt");
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

        questions = myList;

    }

    public void listQuestions(){


        for(Question q : this.questions){
            VBox vbox = new VBox();
            TitledPane tp = new TitledPane(q.getQuestionField().toString(), vbox);
            for(Answer a : q.getAnswers()){
                if(a instanceof CorrectAnswer){
                    vbox.getChildren().add(new Label(a.getAnswerText() + " (correct)"));
                } else {
                    vbox.getChildren().add(new Label(a.getAnswerText()));
                }
            }
            Button modifyButton = new Button("modify question");
            modifyButton.setOnAction(e -> {
                this.modifyQuestion(q);
            });
            vbox.getChildren().add(modifyButton);



            this.accordion.getPanes().add(tp);
        }
    }

    public void modifyQuestion(Question q){
        Stage qModStage = new Stage();
        qModStage.setTitle("Modify question");
        BorderPane layout = new BorderPane();
        VBox vbox = new VBox();
        layout.setCenter(vbox);
        ArrayList<Answer> answers = q.getAnswers();

        TextField questionTextField = new TextField(q.getQuestionField().toString());
        TextField firstAnswerField = new TextField(answers.get(0).getAnswerText());
        TextField secondAnswerField = new TextField(answers.get(1).getAnswerText());
        TextField thirdAnswerField = new TextField(answers.get(2).getAnswerText());
        TextField fourthAnswerField = new TextField(answers.get(3).getAnswerText());

        Button changeQuestionButton = new Button("change question");
        changeQuestionButton.setOnAction(e -> {
            q.getQuestionField().setQuestionText(questionTextField.getText());
            answers.get(0).setAnswer(firstAnswerField.getText());
            answers.get(1).setAnswer(secondAnswerField.getText());
            answers.get(2).setAnswer(thirdAnswerField.getText());
            answers.get(3).setAnswer(fourthAnswerField.getText());
            qModStage.close();
            this.writeToFile();
            this.accordion = new Accordion();
            mainLayout.setCenter(accordion);
            this.listQuestions();
        });

        vbox.getChildren().addAll(questionTextField, firstAnswerField, secondAnswerField, thirdAnswerField, fourthAnswerField, changeQuestionButton );
        Scene modScene = new Scene(layout);
        qModStage.setScene(modScene);
        qModStage.showAndWait();
    }

    public void writeToFile(){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try{
            fout = new FileOutputStream("C:\\Users\\shand\\IdeaProjects\\myworkin\\questions.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this.questions);
            oos.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
    }

    public Scene getScene(Scene lScene){
        this.lastScene = lScene;
        Scene scene = new Scene(mainLayout);
        getQuestions();
        listQuestions();
        return scene;
    }
}
