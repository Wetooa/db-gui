package com.example.csit228_f1_v2.controller;

import com.example.csit228_f1_v2.queries.auth.LoginQuery;
import com.example.csit228_f1_v2.queries.auth.RegisterQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.csit228_f1_v2.Application.a;
import static com.example.csit228_f1_v2.Application.stage;

public class AuthController {


    @FXML
    private TextField fieldUsername;

    @FXML
    private PasswordField fieldPassword;


    public void handleLogin() {

        try {
            LoginQuery.POST(fieldUsername.getText(), fieldPassword.getText());
        } catch (Exception e) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
            a.show();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/csit228_f1_v2/dashboard-view.fxml"));
            Parent p = loader.load();
            Scene s = new Scene(p);
            DashboardController.controller = loader.getController();
            stage.setScene(s);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("An error occurred!");
            a.show();
        }
    }


    public void handleRegister() {
        try {
            RegisterQuery.POST(fieldUsername.getText(), fieldPassword.getText());
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("User registered successfully");
        } catch (Exception e) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText(e.getMessage());
        }
        a.show();

    }

}


