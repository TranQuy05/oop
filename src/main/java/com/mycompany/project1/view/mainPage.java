/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author dtquy
 */
public class mainPage {
    private BorderPane root;

    public Node showMainPage() {
        VBox mainContainer = new VBox(30);
        mainContainer.setPadding(new Insets(20));

        // Create a horizontal container for info cards
        HBox cardsContainer = new HBox(20);
        cardsContainer.setAlignment(Pos.CENTER);
        cardsContainer.setPadding(new Insets(10));

        // Thẻ thông tin: Tổng số hội viên
        VBox hvBox = createInfoCard(" Tổng hội viên", "123");
        VBox khoaBox = createInfoCard("️ Khóa học đang mở", "5");
        VBox doanhThuBox = createInfoCard(" Doanh thu tháng", "50,000,000đ");
        VBox lichBox = createInfoCard(" Lịch tuần này", "12 buổi");
        VBox xacThuc = createInfoCard("Xác thực hội viên","");

        // Add all cards to the horizontal container
        cardsContainer.getChildren().addAll(hvBox, khoaBox, doanhThuBox, lichBox, xacThuc);

        // Create a container for charts
        VBox chartsContainer = new VBox(30);
        chartsContainer.setAlignment(Pos.CENTER);
        chartsContainer.setPadding(new Insets(20, 0, 0, 0));

        // First row of charts
        HBox topCharts = new HBox(20);
        topCharts.setAlignment(Pos.CENTER);
        HBox.setHgrow(topCharts, Priority.ALWAYS);

        // Biểu đồ cột: số lượng khách hàng trong 6 tháng
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Tháng");
        yAxis.setLabel("Số lượng khách hàng");

        XYChart.Series<String, Number> barData = new XYChart.Series<>();
        barData.setName("Khách hàng");
        barData.getData().add(new XYChart.Data<>("1", 50));
        barData.getData().add(new XYChart.Data<>("2", 60));
        barData.getData().add(new XYChart.Data<>("3", 45));
        barData.getData().add(new XYChart.Data<>("4", 70));
        barData.getData().add(new XYChart.Data<>("5", 80));
        barData.getData().add(new XYChart.Data<>("6", 90));

        barChart.getData().add(barData);
        HBox.setHgrow(barChart, Priority.ALWAYS);
        barChart.setPrefHeight(300);

        // Biểu đồ đường: doanh thu trong 6 tháng
        NumberAxis yLineAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(new CategoryAxis(), yLineAxis);
        lineChart.setTitle("Doanh thu 6 tháng gần nhất");
        yLineAxis.setLabel("Doanh thu (triệu VNĐ)");

        XYChart.Series<String, Number> lineData = new XYChart.Series<>();
        lineData.setName("Doanh thu");
        lineData.getData().add(new XYChart.Data<>("1", 100));
        lineData.getData().add(new XYChart.Data<>("2", 120));
        lineData.getData().add(new XYChart.Data<>("3", 90));
        lineData.getData().add(new XYChart.Data<>("4", 130));
        lineData.getData().add(new XYChart.Data<>("5", 150));
        lineData.getData().add(new XYChart.Data<>("6", 160));

        lineChart.getData().add(lineData);
        HBox.setHgrow(lineChart, Priority.ALWAYS);
        lineChart.setPrefHeight(300);

        topCharts.getChildren().addAll(barChart, lineChart);

        // Biểu đồ tròn: các loại thẻ tập
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
            new PieChart.Data("Thẻ tháng", 40),
            new PieChart.Data("Thẻ quý", 30),
            new PieChart.Data("Thẻ năm", 30)
        );
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("Tỉ lệ loại thẻ tập đã bán");
        pieChart.setPrefSize(400, 400);

        // Create a container for the pie chart aligned to the left
        HBox pieContainer = new HBox();
        pieContainer.setAlignment(Pos.CENTER_LEFT);
        pieContainer.setPadding(new Insets(20, 0, 0, 20));
        pieContainer.getChildren().add(pieChart);

        // Add all components to the main container
        mainContainer.getChildren().addAll(cardsContainer, topCharts, pieContainer);

        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        return scrollPane;
    }

    private VBox createInfoCard(String title, String value) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));
        box.setPrefSize(200, 120);
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
