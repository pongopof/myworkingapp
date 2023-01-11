package com.example.appindev;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AButton extends Button {
    private Answer answer;
    private Millioner millioner;
    private HBox answersBox;


    public AButton(Answer answer, String string, Millioner mill){
        this.answer = answer;
        this.setText(string);
        this.setOnAction((event) -> {
            mill.givedAnswer(this);
        });
        this.answersBox = new HBox();
        this.answersBox.setSpacing(10);
        Label AText = new Label(this.answer.getAnswerText());
        this.answersBox.getChildren().addAll(this, AText);



        VBox buttonsPane = mill.getButtonsPane();
        buttonsPane.getChildren().add(this.answersBox);
    }



    public Answer getAnswer(){
        return answer;
    }



}
