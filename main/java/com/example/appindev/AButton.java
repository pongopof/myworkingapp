package com.example.appindev;

import javafx.scene.control.Button;

public class AButton extends Button {
    private Answer answer;
    private Millioner millioner;


    public AButton(Answer answer, Millioner mill){
        this.answer = answer;
        this.setText(answer.getAnswerText());
        this.setOnAction((event) -> {
            mill.givedAnswer(this);
        });
    }

    public Answer getAnswer(){
        return answer;
    }



}
