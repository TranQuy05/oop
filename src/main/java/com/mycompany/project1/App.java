package com.mycompany.project1;

import com.mycompany.project1.view.HoiVienView;
import com.mycompany.project1.view.KhoaHocView;
import com.mycompany.project1.view.LoginView;
import com.mycompany.project1.view.LopHocView;
import com.mycompany.project1.view.NhanVienView;
import com.mycompany.project1.view.NhatKyTapLuyenView;
import com.mycompany.project1.view.QuanLyTheView;
import com.mycompany.project1.view.PaymentHistoryView;
import com.mycompany.project1.view.GoiDangKyView;

import com.mycompany.project1.view.mainPage;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Button currentSelectedButton; // Thêm biến để theo dõi button đang được chọn

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

        HBox logoBox = new HBox(logoView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setPadding(new Insets(10));

        VBox mainMenu = new VBox(10);
        mainMenu.setStyle("-fx-background-color: #333; -fx-padding: 10px;");
        mainMenu.setPrefWidth(200);
        Button btn1 = createMenuButton("Hội Viên");
        Button btn2 = createMenuButton("Nhân Viên");
        Button btn3 = createMenuButton("Gói tập");
        Button btn4 = createMenuButton("Lớp Học ");
        Button btn5 = createMenuButton("Trang Chủ");
        Button btn6 = createMenuButton("Nhật ký tập luyện");
        Button btn7 = createMenuButton("Quản lý thẻ");
        Button btn8 = createMenuButton("Lịch sử thanh toán");
        
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn5.setMaxWidth(Double.MAX_VALUE);
        btn6.setMaxWidth(Double.MAX_VALUE);
        btn7.setMaxWidth(Double.MAX_VALUE);
        btn8.setMaxWidth(Double.MAX_VALUE);
        mainMenu.getChildren().addAll(logoBox,btn5, btn1, btn2, btn3,btn4 , btn6, btn7, btn8);

        // Set logo click event after btn5 is created
        logoView.setOnMouseClicked(event -> {
            root.setCenter(main.showMainPage()); 
            resetButtonStyle(btn5); // Reset style khi click vào logo
        });

        VBox bottomMenu = new VBox(10);
        bottomMenu.setStyle(" -fx-padding: 10; ");
        bottomMenu.setAlignment(Pos.BOTTOM_LEFT);
        Button btnLogOut = createMenuButton("Đăng xuất");
        btnLogOut.setMaxWidth(Double.MAX_VALUE);
        Button btnSetInfor = createMenuButton("Thay đổi thông tin");
        btnSetInfor.setMaxWidth(Double.MAX_VALUE);
        bottomMenu.getChildren().addAll(btnLogOut, btnSetInfor);

        // Thêm xử lý sự kiện đăng xuất
        btnLogOut.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận đăng xuất");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có muốn đăng xuất?");
            
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    LoginView loginView = new LoginView();
                    loginView.showLogin(stage, () -> showMainApp(stage));
                }
            });
        });

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
        btn2.setOnAction(e -> {
            root.setCenter(nhanvien.showEmployeePage());
            resetButtonStyle(btn2);
        });
        
        HoiVienView hoivien = new HoiVienView(root);
        btn1.setOnAction(e -> {
            root.setCenter(hoivien.showHoiVien());
            resetButtonStyle(btn1);
        });
        
        GoiDangKyView goidangKy = new GoiDangKyView(root);
        btn3.setOnAction(e -> {
            root.setCenter(goidangKy.showGoiDangKy());
            resetButtonStyle(btn3);
        });
        
        LopHocView lophocview = new LopHocView(root);
        btn4.setOnAction(e -> {
            root.setCenter(lophocview.showLopHoc());
            resetButtonStyle(btn4);
        });
        
        btn5.setOnAction(e -> {
            root.setCenter(main.showMainPage());
            resetButtonStyle(btn5);
        });

        NhatKyTapLuyenView nhatKyView = new NhatKyTapLuyenView(root);
        btn6.setOnAction(e -> {
            root.setCenter(nhatKyView.showNhatKyTapLuyen());
            resetButtonStyle(btn6);
        });

        QuanLyTheView quanLyTheView = new QuanLyTheView(root);
        btn7.setOnAction(e -> {
            root.setCenter(quanLyTheView.showQuanLyThe());
            resetButtonStyle(btn7);
        });

        PaymentHistoryView paymentHistoryView = new PaymentHistoryView(root);
        btn8.setOnAction(e -> {
            root.setCenter(paymentHistoryView.showPaymentHistory());
            resetButtonStyle(btn8);
        });
    }

    private void resetButtonStyle(Button selectedButton) {
        // Reset style của button cũ nếu có
        if (currentSelectedButton != null) {
            currentSelectedButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-border-width: 0;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            );
        }
        
        // Set style mới cho button được chọn
        selectedButton.setStyle(
            "-fx-background-color: #00ffcc;" +
            "-fx-text-fill: #333;" +
            "-fx-border-width: 0;" +
            "-fx-font-size: 14px;" +
            "-fx-cursor: hand;"
        );
        
        // Cập nhật button hiện tại
        currentSelectedButton = selectedButton;
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
        btn.setOnMouseEntered(e -> {
            if (btn != currentSelectedButton) {
                btn.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #00ffcc;" +            
                    "-fx-border-width: 0;" +
                    "-fx-font-size: 14px;" +
                    "-fx-cursor: hand;"
                );
            }
        });
    
        // Khi di chuột ra -> trở về bình thường
        btn.setOnMouseExited(e -> {
            if (btn != currentSelectedButton) {
                btn.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-border-width: 0;" +
                    "-fx-font-size: 14px;" +
                    "-fx-cursor: hand;"
                );
            }
        });
    
        return btn;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
