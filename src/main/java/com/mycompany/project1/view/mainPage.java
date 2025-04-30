/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author dtquy
 */
public class mainPage {
     private BorderPane root;
//
//    public mainPage(BorderPane root) {
//        this.root = root;
//    }

    public Node showMainPage() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.TOP_CENTER);

        // Thẻ thông tin: Tổng số hội viên
        VBox hvBox = createInfoCard(" Tổng hội viên", "123");
        VBox khoaBox = createInfoCard("️ Khóa học đang mở", "5");
        VBox doanhThuBox = createInfoCard(" Doanh thu tháng", "50,000,000đ");
        VBox lichBox = createInfoCard(" Lịch tuần này", "12 buổi");
        VBox xacThuc = createInfoCard("Xác thực hội viên","");

        // Đặt thẻ vào Grid
        grid.add(hvBox, 0, 0);
        grid.add(khoaBox, 1, 0);
        grid.add(doanhThuBox, 0, 1);
        grid.add(lichBox, 1, 1);
        grid.add(xacThuc,0,2);
        
        return grid;
    }

    private VBox createInfoCard(String title, String value) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));
        box.setPrefSize(250, 100);
        box.setBackground(new Background(new BackgroundFill(Color.web("#f0f0f0"), new CornerRadii(12), Insets.EMPTY)));
        box.setStyle("-fx-border-color: #ccc; -fx-border-radius: 12px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 16));
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", 24));
        valueLabel.setStyle("-fx-font-weight: bold;");

        box.getChildren().addAll(titleLabel, valueLabel);
        return box;
    }
}
