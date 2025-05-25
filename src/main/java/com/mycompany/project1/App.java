package com.mycompany.project1;

import com.mycompany.project1.view.HoiVienView;
import com.mycompany.project1.view.KhoaHocView;
import com.mycompany.project1.view.LoginView;
import com.mycompany.project1.view.LopHocView;
import com.mycompany.project1.view.NhanVienView;

import com.mycompany.project1.view.mainPage;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private BorderPane root;

    

    @Override
    public void start(Stage primaryStage) {
        
        

            LoginView loginView = new LoginView();
       
        loginView.showLogin(primaryStage, () -> {showMainApp(primaryStage);
                
                });
        
    }
    // màn hình chính
     
    private void showMainApp(Stage stage) {
        root = new BorderPane();
        mainPage main = new mainPage();
        root.setCenter(main.showMainPage());
        Image logo = new Image("file:src/main/resources/logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(45);
        logoView.setFitHeight(45);
        logoView.setStyle("-fx-cursor: hand;"); 
        logoView.setOnMouseClicked(event -> {
            root.setCenter(main.showMainPage()); 
        });

        HBox logoBox = new HBox(logoView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setPadding(new Insets(10));

        VBox mainMenu = new VBox(10);
        mainMenu.setStyle("-fx-background-color: #333; -fx-padding: 10px;");
        mainMenu.setPrefWidth(200);
        Button btn1 = createMenuButton("Hội Viên");
        Button btn2 = createMenuButton("Nhân Viên");
        Button btn3 = createMenuButton("Khóa Học");
        Button btn4 = createMenuButton("Lớp Học ");
        Button btn5 = createMenuButton("Trang Chủ");
        
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);
        mainMenu.getChildren().addAll(logoBox,btn5, btn1, btn2, btn3);

        VBox bottomMenu = new VBox(10);
        bottomMenu.setStyle(" -fx-padding: 10; ");
        bottomMenu.setAlignment(Pos.BOTTOM_LEFT);
        Button btnLogOut = createMenuButton("Đăng xuất");
        btnLogOut.setMaxWidth(Double.MAX_VALUE);
        Button btnSetInfor = createMenuButton("Thay đổi thông tin");
        btnSetInfor.setMaxWidth(Double.MAX_VALUE);
        //btnLogOut.setStyle(" -fx-background-color: #555; -fx-text-fill: white; ");
        //btnSetInfor.setStyle(" -fx-background-color: #555; -fx-text-fill:white;");
        bottomMenu.getChildren().addAll(btnLogOut, btnSetInfor);

        BorderPane leftPane = new BorderPane();
        leftPane.setTop(mainMenu);
        leftPane.setBottom(bottomMenu);
        leftPane.setStyle(" -fx-background-color: #333;");
        root.setLeft(leftPane);

        Scene scene = new Scene(root, 1200, 800, Color.LIGHTGRAY);
        stage.setScene(scene);
        stage.setTitle("Pantheon's Fitness");
        
  
        stage.setMinWidth(1000);
        stage.setMinHeight(600);
        
       
        stage.centerOnScreen();
        
        stage.show();
        stage.setMaximized(true);
        
        NhanVienView nhanvien = new NhanVienView(root);
        btn2.setOnAction(e -> root.setCenter(nhanvien.showEmployeePage()));
        HoiVienView hoivien = new HoiVienView(root);
        btn1.setOnAction(e -> root.setCenter(hoivien.showHoiVien()));
        KhoaHocView khoahocview = new KhoaHocView(root);
        btn3.setOnAction(e -> root.setCenter(khoahocview.showKhoaHoc()));
        LopHocView lophocview = new LopHocView(root);
        btn4.setOnAction(e -> root.setCenter(lophocview.showLopHoc()));
        btn5.setOnAction(e -> root.setCenter(main.showMainPage()));
    }
    private Button createMenuButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle(
            "-fx-background-color: transparent;" +  
            "-fx-text-fill: white;" +               
            "-fx-border-width: 0;" +                
            "-fx-font-size: 14px;" +
            "-fx-cursor: hand;"                     
        );
    
        // Khi di chuột vào -> sáng lên
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #00ffcc;" +            
            "-fx-border-width: 0;" +
            "-fx-font-size: 14px;" +
            "-fx-cursor: hand;"
        ));
    
        // Khi di chuột ra -> trở về bình thường
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: white;" +
            "-fx-border-width: 0;" +
            "-fx-font-size: 14px;" +
            "-fx-cursor: hand;"
        ));
    
        return btn;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
