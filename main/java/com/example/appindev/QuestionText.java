package com.example.appindev;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class QuestionText implements Questionable{


    private String qText;

    public QuestionText(String text){
        qText = text;
    }

    public void initialize(Millioner mill){

        BorderPane pane = mill.getMainPane();
        Label top = new Label(qText);
        pane.setTop(top);

    }
}
