package com.example.csc311_card_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardGameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CardGameApplication.class.getResource("CardGame.fxml"));
        //I set the size
        Scene scene = new Scene(fxmlLoader.load(), 600, 327);
        stage.setTitle("Ready to play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}