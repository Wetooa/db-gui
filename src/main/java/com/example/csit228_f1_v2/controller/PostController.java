package com.example.csit228_f1_v2.controller;

import com.example.csit228_f1_v2.Application;
import com.example.csit228_f1_v2.classes.Post;
import com.example.csit228_f1_v2.data.Storage;
import com.example.csit228_f1_v2.queries.post.PostQuery;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PostController {


    public Post post;

    @FXML
    private Label textUsername;
    @FXML
    private Label textMessage;
    @FXML
    private Label textUpdatedAt;

    @FXML
    private TextArea textareaUpdate;


    public void setTextUsername(String text) {
        textUsername.setText(text);
    }

    public void setTextMessage(String text) {
        textMessage.setText(text);
    }

    public void setTextUpdatedAt(String text) {
        textUpdatedAt.setText(text);
    }

    public void updatePost() {
        if (!Storage.user.getId().equals(post.getPosterId())) {
            Application.a.setAlertType(Alert.AlertType.WARNING);
            Application.a.setContentText("Can't update this post, not you own duh");
            Application.a.show();
            return;
        }

        String newMessage = textareaUpdate.getText();
        PostQuery.UPDATE(post.getId(), newMessage);
        textareaUpdate.setText("");
        DashboardController.controller.display();
    }

    public void deletePost() {
        if (!Storage.user.getId().equals(post.getPosterId())) {
            Application.a.setAlertType(Alert.AlertType.WARNING);
            Application.a.setContentText("Can't delete this post, not you own duh");
            Application.a.show();
            return;
        }
        PostQuery.DELETE(post.getId());
        DashboardController.controller.display();
    }


}
