package com.mycompany.project1.view;

import com.mycompany.project1.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private LoginController controller = new LoginController();

    public void showLogin(Stage stage, Runnable onLoginSuccess) {
        Label lblUser = new Label("Tài khoản:");
        TextField txtUser = new TextField();
        Label lblPass = new Label("Mật khẩu:");
        PasswordField txtPass = new PasswordField();

        Button btnLogin = new Button("Đăng nhập");
        Label lblStatus = new Label();

        btnLogin.setOnAction(e -> {
            String user = txtUser.getText();
            String pass = txtPass.getText();
            if (controller.authenticate(user, pass)) {
                onLoginSuccess.run(); // Gọi khi đăng nhập thành công
            } else {
                lblStatus.setText("Sai tài khoản hoặc mật khẩu");
            }
        });

        VBox loginBox = new VBox(10, lblUser, txtUser, lblPass, txtPass, btnLogin, lblStatus);
        loginBox.setPadding(new Insets(20));
        loginBox.setAlignment(Pos.CENTER);
        
            
        Scene scene = new Scene(loginBox, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Đăng nhập");
        stage.show();
    }
}
