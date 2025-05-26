/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.controller.HoiVienController;
import com.mycompany.project1.controller.TheHoiVienController;
import com.mycompany.project1.model.HoiVien;
import com.mycompany.project1.model.TheHoiVien;
import com.mycompany.project1.model.GoiDangKy;
import com.mycompany.project1.model.Payment;
import com.mycompany.project1.DAO.TheHoiVienDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author dtquy
 */
public class mainPage {
    private BorderPane root;
    private HoiVienController hoiVienController;
    private TheHoiVienController theHoiVienController;

    public mainPage() {
        this.hoiVienController = new HoiVienController();
        this.theHoiVienController = new TheHoiVienController();
    }

    public Node showMainPage() {
        VBox mainContainer = new VBox(30);
        mainContainer.setPadding(new Insets(20));

        // Create a horizontal container for info cards
        HBox cardsContainer = new HBox(20);
        cardsContainer.setAlignment(Pos.CENTER);
        cardsContainer.setPadding(new Insets(10));

        // Thẻ thông tin: Tổng số hội viên
        VBox hvBox = createInfoCard("Tổng hội viên", String.valueOf(hoiVienController.getTongSoHoiVien()));
        VBox khoaBox = createInfoCard("Gói tập hiện có", String.valueOf(theHoiVienController.getTongSoGoiTap()));
        VBox doanhThuBox = createInfoCard("Doanh thu tháng", "50,000,000đ");
        
        // Thẻ xác thực hội viên với nút click
        VBox xacThuc = createInfoCard("Xác thực hội viên", "Click để xác thực");
        xacThuc.setOnMouseClicked(e -> showVerificationDialog());

        // Add all cards to the horizontal container
        cardsContainer.getChildren().addAll(hvBox, khoaBox, doanhThuBox, xacThuc);

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

    private void showVerificationDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Xác thực hội viên");
        dialog.setHeaderText("Nhập mã hội viên để xác thực");

        // Set the button types
        ButtonType verifyButtonType = new ButtonType("Xác thực", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(verifyButtonType, ButtonType.CANCEL);

        // Create the custom content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField maHoiVienField = new TextField();
        maHoiVienField.setPromptText("Nhập mã hội viên");

        grid.add(new Label("Mã hội viên:"), 0, 0);
        grid.add(maHoiVienField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a maHoiVien when the verify button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == verifyButtonType) {
                try {
                    int maHoiVien = Integer.parseInt(maHoiVienField.getText());
                    showMemberDetails(maHoiVien);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Mã hội viên không hợp lệ!");
                    alert.showAndWait();
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showMemberDetails(int maHoiVien) {
        HoiVien hoiVien = hoiVienController.timHoiVienTheoMa(maHoiVien);
        if (hoiVien == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Không tìm thấy hội viên!");
            alert.showAndWait();
            return;
        }

        // Create a new window to show member details
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.APPLICATION_MODAL);
        detailsStage.setTitle("Thông tin hội viên");

        TabPane tabPane = new TabPane();

        // Tab thông tin cá nhân
        Tab personalInfoTab = new Tab("Thông tin cá nhân");
        personalInfoTab.setContent(createPersonalInfoTab(hoiVien));
        personalInfoTab.setClosable(false);

        // Tab thẻ tập
        Tab membershipTab = new Tab("Thẻ tập");
        membershipTab.setContent(createMembershipTab(maHoiVien));
        membershipTab.setClosable(false);

        // Tab gói tập
        Tab subscriptionTab = new Tab("Gói tập");
        subscriptionTab.setContent(createSubscriptionTab(maHoiVien));
        subscriptionTab.setClosable(false);

        // Tab lịch sử thanh toán
        Tab paymentTab = new Tab("Lịch sử thanh toán");
        paymentTab.setContent(createPaymentHistoryTab(maHoiVien));
        paymentTab.setClosable(false);

        // Thêm nút đăng ký thẻ và gói tập
        HBox buttonContainer = new HBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(10));

        Button registerCardBtn = new Button("Đăng ký thẻ mới");
        registerCardBtn.setOnAction(e -> showRegisterCardDialog(maHoiVien));

        Button registerSubscriptionBtn = new Button("Đăng ký gói tập");
        registerSubscriptionBtn.setOnAction(e -> showRegisterSubscriptionDialog(maHoiVien));

        buttonContainer.getChildren().addAll(registerCardBtn, registerSubscriptionBtn);

        VBox mainContainer = new VBox(10);
        mainContainer.getChildren().addAll(tabPane, buttonContainer);

        Scene scene = new Scene(mainContainer, 800, 600);
        detailsStage.setScene(scene);
        detailsStage.showAndWait();
    }

    private Node createPersonalInfoTab(HoiVien hoiVien) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Mã hội viên:"), 0, 0);
        grid.add(new Label(String.valueOf(hoiVien.getMaHoiVien())), 1, 0);
        grid.add(new Label("Họ tên:"), 0, 1);
        grid.add(new Label(hoiVien.getHoTen()), 1, 1);
        grid.add(new Label("Năm sinh:"), 0, 2);
        grid.add(new Label(String.valueOf(hoiVien.getNamSinh())), 1, 2);
        grid.add(new Label("Số điện thoại:"), 0, 3);
        grid.add(new Label(String.valueOf(hoiVien.getSdt())), 1, 3);
        grid.add(new Label("Giới tính:"), 0, 4);
        grid.add(new Label(hoiVien.getGioiTinh()), 1, 4);
        grid.add(new Label("Địa chỉ:"), 0, 5);
        grid.add(new Label(hoiVien.getDiaChi()), 1, 5);
        grid.add(new Label("Email:"), 0, 6);
        grid.add(new Label(hoiVien.getEmail()), 1, 6);
        grid.add(new Label("CCCD:"), 0, 7);
        grid.add(new Label(hoiVien.getCanCuocCongDan()), 1, 7);

        return new ScrollPane(grid);
    }

    private Node createMembershipTab(int maHoiVien) {
        TableView<TheHoiVien> tableView = new TableView<>();
        
        TableColumn<TheHoiVien, Integer> maTheCol = new TableColumn<>("Mã thẻ");
        maTheCol.setCellValueFactory(new PropertyValueFactory<>("cardID"));
        
        TableColumn<TheHoiVien, String> loaiTheCol = new TableColumn<>("Loại thẻ");
        loaiTheCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        TableColumn<TheHoiVien, Double> giaCol = new TableColumn<>("Giá");
        giaCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<TheHoiVien, Integer> thoiHanCol = new TableColumn<>("Thời hạn (tháng)");
        thoiHanCol.setCellValueFactory(new PropertyValueFactory<>("validDuration"));
        
        TableColumn<TheHoiVien, LocalDate> ngayMuaCol = new TableColumn<>("Ngày mua");
        ngayMuaCol.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        
        tableView.getColumns().addAll(maTheCol, loaiTheCol, giaCol, thoiHanCol, ngayMuaCol);
        
        // Load data from DAO
        TheHoiVienDAO theHoiVienDAO = new TheHoiVienDAO();
        tableView.setItems(FXCollections.observableArrayList(theHoiVienDAO.getMemberCards(maHoiVien)));
        
        return tableView;
    }

    private Node createSubscriptionTab(int maHoiVien) {
        TableView<GoiDangKy> tableView = new TableView<>();
        
        TableColumn<GoiDangKy, String> tenGoiCol = new TableColumn<>("Tên gói");
        tenGoiCol.setCellValueFactory(new PropertyValueFactory<>("subscriptionName"));
        
        TableColumn<GoiDangKy, String> loaiGoiCol = new TableColumn<>("Loại gói");
        loaiGoiCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        TableColumn<GoiDangKy, LocalDate> ngayBatDauCol = new TableColumn<>("Ngày bắt đầu");
        ngayBatDauCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        
        TableColumn<GoiDangKy, String> chiTietCol = new TableColumn<>("Chi tiết");
        chiTietCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        
        TableColumn<GoiDangKy, String> trangThaiCol = new TableColumn<>("Trạng thái");
        trangThaiCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        tableView.getColumns().addAll(tenGoiCol, loaiGoiCol, ngayBatDauCol, chiTietCol, trangThaiCol);
        
        // Load data from DAO
        TheHoiVienDAO theHoiVienDAO = new TheHoiVienDAO();
        tableView.setItems(FXCollections.observableArrayList(theHoiVienDAO.getMemberSubscriptions(maHoiVien)));
        
        return tableView;
    }

    private Node createPaymentHistoryTab(int maHoiVien) {
        TableView<Payment> tableView = new TableView<>();
        
        TableColumn<Payment, Integer> maThanhToanCol = new TableColumn<>("Mã thanh toán");
        maThanhToanCol.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        
        TableColumn<Payment, String> tenGoiCol = new TableColumn<>("Tên gói");
        tenGoiCol.setCellValueFactory(new PropertyValueFactory<>("subscriptionName"));
        
        TableColumn<Payment, String> loaiTheCol = new TableColumn<>("Loại thẻ");
        loaiTheCol.setCellValueFactory(new PropertyValueFactory<>("cardType"));
        
        TableColumn<Payment, LocalDate> ngayThanhToanCol = new TableColumn<>("Ngày thanh toán");
        ngayThanhToanCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        
        TableColumn<Payment, String> hinhThucCol = new TableColumn<>("Hình thức");
        hinhThucCol.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        
        tableView.getColumns().addAll(maThanhToanCol, tenGoiCol, loaiTheCol, ngayThanhToanCol, hinhThucCol);
        
        // Load data from DAO
        TheHoiVienDAO theHoiVienDAO = new TheHoiVienDAO();
        tableView.setItems(FXCollections.observableArrayList(theHoiVienDAO.getMemberPayments(maHoiVien)));
        
        return tableView;
    }

    private void showRegisterCardDialog(int memberID) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Đăng ký thẻ mới");
        dialog.setHeaderText("Chọn loại thẻ và phương thức thanh toán");

        // Set the button types
        ButtonType registerButtonType = new ButtonType("Đăng ký", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        // Create the custom content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<String> cardTypeCombo = new ComboBox<>();
        TheHoiVienDAO theHoiVienDAO = new TheHoiVienDAO();
        cardTypeCombo.setItems(theHoiVienDAO.getCardTypes());

        ComboBox<String> paymentTypeCombo = new ComboBox<>();
        paymentTypeCombo.getItems().addAll("Cash", "Credit Card", "Online");

        grid.add(new Label("Loại thẻ:"), 0, 0);
        grid.add(cardTypeCombo, 1, 0);
        grid.add(new Label("Phương thức thanh toán:"), 0, 1);
        grid.add(paymentTypeCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a registration when the register button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == registerButtonType) {
                if (cardTypeCombo.getValue() == null || paymentTypeCombo.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng chọn đầy đủ thông tin!");
                    alert.showAndWait();
                    return null;
                }

                TheHoiVien selectedCard = theHoiVienDAO.layTheTheoLoai(cardTypeCombo.getValue());
                if (selectedCard != null) {
                    boolean success = theHoiVienDAO.dangKyTheTap(memberID, selectedCard.getCardID(), paymentTypeCombo.getValue());
                    if (success) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công");
                        alert.setHeaderText(null);
                        alert.setContentText("Đăng ký thẻ thành công!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Đăng ký thẻ thất bại!");
                        alert.showAndWait();
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showRegisterSubscriptionDialog(int memberID) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Đăng ký gói tập");
        dialog.setHeaderText("Chọn gói tập và phương thức thanh toán");

        // Set the button types
        ButtonType registerButtonType = new ButtonType("Đăng ký", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        // Create the custom content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<GoiDangKy> subscriptionCombo = new ComboBox<>();
        TheHoiVienDAO theHoiVienDAO = new TheHoiVienDAO();
        subscriptionCombo.setItems(FXCollections.observableArrayList(theHoiVienDAO.getAllSubscriptions()));

        ComboBox<String> paymentTypeCombo = new ComboBox<>();
        paymentTypeCombo.getItems().addAll("Cash", "Credit Card", "Online");

        grid.add(new Label("Gói tập:"), 0, 0);
        grid.add(subscriptionCombo, 1, 0);
        grid.add(new Label("Phương thức thanh toán:"), 0, 1);
        grid.add(paymentTypeCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a registration when the register button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == registerButtonType) {
                if (subscriptionCombo.getValue() == null || paymentTypeCombo.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Vui lòng chọn đầy đủ thông tin!");
                    alert.showAndWait();
                    return null;
                }

                GoiDangKy selectedSubscription = subscriptionCombo.getValue();
                boolean success = theHoiVienDAO.themGoiDangKy(memberID, selectedSubscription);
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng ký gói tập thành công!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng ký gói tập thất bại!");
                    alert.showAndWait();
                }
            }
            return null;
        });

        dialog.showAndWait();
    }
}
