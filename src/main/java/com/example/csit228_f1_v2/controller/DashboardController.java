package com.example.csit228_f1_v2.controller;

import com.example.csit228_f1_v2.Application;
import com.example.csit228_f1_v2.classes.Post;
import com.example.csit228_f1_v2.data.Storage;
import com.example.csit228_f1_v2.queries.post.PostQuery;
import com.example.csit228_f1_v2.queries.profile.ProfileQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;


public class DashboardController {

    public static DashboardController controller;

    @FXML
    private VBox paneAllPosts;
    @FXML
    private VBox paneMyPosts;

    @FXML
    private TextArea textMessage;

    @FXML
    private Button btnPost;


    @FXML
    private TextField fieldUpdateUsername;

    @FXML
    private PasswordField fieldUpdatePassword;


    @FXML
    private Label textCurrentUsername;


    @FXML
    public void submit() {
        String message = textMessage.getText();
        PostQuery.POST(message);

        textMessage.setText("");
        display();
    }


    @FXML
    public void clear() {
        textMessage.setText("");
    }

    @FXML
    public void initialize() {
        display();
    }

    public void display() {

        textCurrentUsername.setText(Storage.user.getUsername());


        Storage.posts = PostQuery.GET();
        paneAllPosts.getChildren().clear();
        paneMyPosts.getChildren().clear();
        GridPane post = null;

        for (Post p : Storage.posts) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/csit228_f1_v2/post.fxml"));
                post = loader.load();

                PostController controller = loader.getController();
                controller.post = p;

                // TODO: change into actual name of user
                controller.setTextUsername(String.valueOf(p.getUsername()));
                controller.setTextMessage(p.getMessage());
                controller.setTextUpdatedAt(p.getUpdatedAt());


            } catch (IOException e) {
                e.printStackTrace();
            }

            paneAllPosts.getChildren().add(post);
        }

        for (Post p : Storage.posts) {
            if (!p.getPosterId().equals(Storage.user.getId())) continue;

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/csit228_f1_v2/post.fxml"));
                post = loader.load();

                PostController controller = loader.getController();
                controller.post = p;

                // TODO: change into actual name of user
                controller.setTextUsername(String.valueOf(p.getUsername()));
                controller.setTextMessage(p.getMessage());
                controller.setTextUpdatedAt(p.getUpdatedAt());


            } catch (IOException e) {
                e.printStackTrace();
            }

            paneMyPosts.getChildren().add(post);
        }

    }


    public void updateUser() {
        String username = fieldUpdateUsername.getText();
        String password = fieldUpdatePassword.getText();
        try {
            ProfileQuery.UPDATE(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        display();
    }


    public void clearUpdateFields() {
        fieldUpdateUsername.setText("");
        fieldUpdatePassword.setText("");
    }


    public void deleteAccount() {
        try {
            ProfileQuery.DELETE();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logout();
    }


    public void logout() {
        Storage.user = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/csit228_f1_v2/auth-view.fxml"));
        try {
            Parent p = loader.load();
            Scene scene = new Scene(p, 700, 500, Color.BLACK);
            Application.stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
