package com.example.appindev;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ImageQuestion implements Questionable{

    private final String questionText;
    private final Image image;
    public ImageView imgv;

    public ImageQuestion(String file, String questionText){

        this.questionText = questionText;
        this.image = new Image(file);
        this.imgv = new ImageView(image);
        imgv.setFitHeight(100);
        imgv.setFitWidth(100);


    }

    public void initialize(Millioner mill){


        HBox topBox = new HBox();
        BorderPane pane = mill.getMainPane();
        Label top = new Label(questionText);
        topBox.getChildren().addAll(top, imgv);
        pane.setTop(topBox);
    }
}
