package com.example.appindev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Hello!");



        VBox openingBox= new VBox();
        Button playMillioner = new Button("Play Millioner");
        Label openingLabel = new Label("Do you want to play Millioner?");
        playMillioner.setOnAction((event) -> {
            Millioner mill = new Millioner();
            stage.setScene(mill.getScene());
        });


        openingBox.getChildren().addAll(openingLabel, playMillioner);
        Scene scene = new Scene(openingBox, 320, 240);
        stage.setScene(scene);





        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}