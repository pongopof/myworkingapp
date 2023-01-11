package com.example.appindev;

import java.io.*;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuestionsCreator implements Serializable {

    private Scene lastScene;
    private String filePath;
    private ArrayList<Question> madeQuestions;
    private ArrayList<Question> readQuestions;
    private TextField questionTextField;
    private TextField correctAnswerTextField;
    private TextField wrongAnswer1TextField;
    private TextField wrongAnswer2TextField;
    private TextField wrongAnswer3TextField;
    private Button enterButton;
    private Button saveButton;
    private Stage stage;



    public QuestionsCreator(Stage stage){
        readQuestions = new ArrayList();
        this.stage = stage;
        this.madeQuestions = new ArrayList<>();

        this.questionTextField = new TextField();
        this.questionTextField.setPromptText("question");
        this.correctAnswerTextField = new TextField();
        this.correctAnswerTextField.setPromptText("correct answer");
        this.wrongAnswer1TextField = new TextField();
        this.wrongAnswer1TextField.setPromptText("first wrong answer");
        this.wrongAnswer2TextField = new TextField();
        this.wrongAnswer2TextField.setPromptText("second wrong answer");
        this.wrongAnswer3TextField = new TextField();
        this.wrongAnswer3TextField.setPromptText("third wrong answer");

        this.enterButton = new Button("Create");

        this.enterButton.setOnAction(e -> this.addQuestion());
        this.saveButton = new Button("save");
        this.saveButton.setOnAction(e -> this.writeToFile());


    }

    public void addQuestion(){
        String questionText = "";
        String correctAnswer = "";
        String wrong1Answer = "";
        String wrong2Answer = "";
        String wrong3Answer = "";

        questionText = questionTextField.getText();
        correctAnswer = correctAnswerTextField.getText();
        wrong1Answer = wrongAnswer1TextField.getText();
        wrong2Answer = wrongAnswer2TextField.getText();
        wrong3Answer = wrongAnswer3TextField.getText();


        this.madeQuestions.add(new Question(questionText, correctAnswer, wrong1Answer, wrong2Answer, wrong3Answer));
        questionTextField.clear();
        correctAnswerTextField.clear();
        wrongAnswer1TextField.clear();
        wrongAnswer2TextField.clear();
        wrongAnswer3TextField.clear();
    }

    public void addQuestions(){

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
        readQuestions = myList;
    }

    public void writeToFile(){


        addQuestions();
        madeQuestions.addAll(this.readQuestions);
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try{
            fout = new FileOutputStream("C:\\Users\\piotr\\eclipse-workspace\\AppInDev\\src\\questions.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this.madeQuestions);
            oos.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
        madeQuestions.clear();
        readQuestions.clear();
    }

    public Scene getScene(Scene lastScene){

        this.lastScene = lastScene;
        VBox answersToGive = new VBox();
        answersToGive.getChildren().addAll(questionTextField, correctAnswerTextField, wrongAnswer1TextField, wrongAnswer2TextField, wrongAnswer3TextField, enterButton, saveButton);
        Scene creatorScene = new Scene(answersToGive);
        System.out.println(this.madeQuestions);
        return creatorScene;

    }


}
