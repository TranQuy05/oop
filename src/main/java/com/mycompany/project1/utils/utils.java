package com.mycompany.project1.utils;

import javafx.scene.control.Alert;

public class utils {
    public static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
