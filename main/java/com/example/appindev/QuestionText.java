package com.example.appindev;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.Serializable;

public class QuestionText implements Questionable, Serializable {


    private String qText;

    public QuestionText(String text){
        qText = text;
    }

    public void initialize(Millioner mill){

        BorderPane pane = mill.getQuestionPane();
        Label top = new Label(qText);
        pane.setTop(top);

    }

    public String toString(){
        return this.qText;
    }
}
