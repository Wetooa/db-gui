package com.example.csit228_f1_v2;

import com.example.csit228_f1_v2.queries.meta.CreateDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static Stage stage;
    public static Alert a;


    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;
        a = new Alert(Alert.AlertType.NONE);

        CreateDB.createDB();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/csit228_f1_v2/auth-view.fxml"));
        AnchorPane paneMain = loader.load();

        Scene scene = new Scene(paneMain, 700, 500, Color.BLACK);
        stage.setScene(scene);
        scene.setFill(Color.CORNFLOWERBLUE);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}
