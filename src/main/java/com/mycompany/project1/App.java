package com.mycompany.project1;

import com.mycompany.project1.view.HoiVienView;
import com.mycompany.project1.view.KhoaHocView;
import com.mycompany.project1.view.LoginView;
import com.mycompany.project1.view.LopHocView;
import com.mycompany.project1.view.NhanVienView;
import com.mycompany.project1.view.ThietBiView;
import com.mycompany.project1.view.mainPage;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        

            LoginView loginView = new LoginView();
       
        loginView.showLogin(primaryStage, () -> {showMainApp(primaryStage);
                
                });
        
    }

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


        VBox mainMenu = new VBox(10);
        mainMenu.setStyle("-fx-background-color: #333; -fx-padding: 10px;");
        mainMenu.setPrefWidth(200);
        Button btn1 = new Button("Hội Viên");
        Button btn2 = new Button("Nhân Viên");
        Button btn3 = new Button("Khóa Học");
        Button btn4 = new Button("Thiết bị");
        Button btn5 = new Button("Lớp Học ");
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn5.setMaxWidth(Double.MAX_VALUE);
        mainMenu.getChildren().addAll(logoView,btn1, btn2, btn3, btn4,btn5);

        VBox bottomMenu = new VBox(10);
        bottomMenu.setStyle(" -fx-padding: 10; ");
        bottomMenu.setAlignment(Pos.BOTTOM_LEFT);
        Button btnLogOut = new Button("Đăng xuất");
         btnLogOut.setMaxWidth(Double.MAX_VALUE);
        Button btnSetInfor = new Button("Thay đổi thông tin");
        btnSetInfor.setMaxWidth(Double.MAX_VALUE);
        btnLogOut.setStyle(" -fx-background-color: #555; -fx-text-fill: white; ");
        btnSetInfor.setStyle(" -fx-background-color: #555; -fx-text-fill:white;");
        bottomMenu.getChildren().addAll(btnLogOut, btnSetInfor);

        BorderPane leftPane = new BorderPane();
        leftPane.setTop(mainMenu);
        leftPane.setBottom(bottomMenu);
        leftPane.setStyle(" -fx-background-color: #333;");
        root.setLeft(leftPane);

        Scene scene = new Scene(root, 800, 600, Color.LIGHTGRAY);
        
        NhanVienView nhanvien = new NhanVienView(root);
        btn2.setOnAction(e -> nhanvien.showEmployeePage());
        HoiVienView hoivien = new HoiVienView(root);
        btn1.setOnAction(e -> hoivien.showHoiVien());
        KhoaHocView khoahocview = new KhoaHocView(root);
        btn3.setOnAction(e-> khoahocview.showKhoaHoc());
        ThietBiView thietbiview = new ThietBiView(root);
        btn4.setOnAction(e-> thietbiview.showThietBi());
        LopHocView lophocview = new LopHocView(root);
        btn5.setOnAction(e-> lophocview.showLopHoc());
        

        stage.setTitle("Pantheon's Fitness");
        stage.setScene(scene);
        stage.show();
    }
}
