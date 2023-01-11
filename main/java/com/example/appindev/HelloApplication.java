package com.example.appindev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class HelloApplication extends Application implements Serializable {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Hello!");



        VBox openingBox= new VBox();
        Button playMillioner = new Button("Play Millioner");
        Button createQuestionButton = new Button("Add new questions");
        Button questionsExplorerButton = new Button("Show custome questions");
        Label openingLabel = new Label("Do you want to play Millioner?");
        Scene scene = new Scene(openingBox, 320, 240);
        CheckBox checkBox1 = new CheckBox("Add custom questions");
        System.out.println("Seba");

        createQuestionButton.setOnAction((event) -> {
            QuestionsCreator creator = new QuestionsCreator(stage);
            stage.setScene(creator.getScene(scene));
        });



        playMillioner.setOnAction((event) -> {
            Millioner mill = new Millioner(stage, "questions.ser");
            if(checkBox1.isSelected()){
                mill.addQuestionBank();
            }
            stage.setScene(mill.getScene(scene));

        });

        questionsExplorerButton.setOnAction((event) -> {
            QuestionsExplorer qe = new QuestionsExplorer(stage);
            stage.setScene(qe.getScene(scene));
        });


        openingBox.getChildren().addAll(openingLabel, playMillioner, checkBox1, createQuestionButton, questionsExplorerButton );


        stage.setScene(scene);











        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}