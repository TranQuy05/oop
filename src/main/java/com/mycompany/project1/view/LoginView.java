package com.mycompany.project1.view;

import com.mycompany.project1.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import com.mycompany.project1.model.User;

public class LoginView {
    private LoginController controller;
    private static User currentUser;
    
    public LoginView() {
        this.controller = new LoginController();
    }

    public void showLoginWindow(Stage stage, Runnable onLoginSuccess) {
        stage.setTitle("Đăng nhập hệ thống");
      
        Image image = new Image("file:src/main/resources/Anh_nen_login.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(750);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
     
 
       
        // Right: Login form
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30, 30, 30, 30));
        formBox.setPrefWidth(200);
        formBox.setStyle("-fx-background-color: #fff;");

        Label title = new Label("Đăng nhập");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextField userTextField = new TextField();
        userTextField.setPromptText("Tên đăng nhập");
        userTextField.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 8 12;");

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Mật khẩu");
        pwBox.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 8 12;");

        Button btn = new Button("Đăng nhập");
        btn.setPrefWidth(120);
        btn.setStyle("-fx-background-color: #111; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #111; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: 15px;"));

        Label actiontarget = new Label();
        actiontarget.setTextFill(javafx.scene.paint.Color.RED);
        actiontarget.setFont(Font.font(13));

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            if (username.isEmpty() || password.isEmpty()) {
                actiontarget.setText("Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            if (controller.authenticate(username, password)) {
                actiontarget.setText("");
                User user = controller.getUser();
                handleSuccessfulLogin(user);
                onLoginSuccess.run();
            } else {
                actiontarget.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        });

        formBox.getChildren().addAll(title, userTextField, pwBox, btn, actiontarget);

        // Main layout
        StackPane root = new StackPane();
       
        root.getChildren().addAll(imageView, formBox);
        StackPane.setAlignment(formBox, Pos.CENTER);

        Scene scene = new Scene(root, 850, 750);
        stage.setScene(scene);
        stage.show();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    private void handleSuccessfulLogin(User user) {
        currentUser = user;
        
    }
}
