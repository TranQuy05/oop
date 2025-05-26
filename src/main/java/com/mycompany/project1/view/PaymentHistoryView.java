package com.mycompany.project1.view;

import com.mycompany.project1.controller.PaymentController;
import com.mycompany.project1.model.Payment;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.geometry.Pos;
import java.time.format.DateTimeFormatter;

public class PaymentHistoryView {
    private BorderPane root;
    private PaymentController controller;
    private TableView<Payment> table;

    public PaymentHistoryView(BorderPane root) {
        this.root = root;
        this.controller = new PaymentController();
    }

    public Node showPaymentHistory() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        // Tạo bảng hiển thị
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(controller.getDanhSachPayment());

        // Add columns
        TableColumn<Payment, Integer> colMa = new TableColumn<>("Mã thanh toán");
        colMa.setCellValueFactory(new PropertyValueFactory<>("paymentID"));

        TableColumn<Payment, Integer> colMaHV = new TableColumn<>("Mã hội viên");
        colMaHV.setCellValueFactory(new PropertyValueFactory<>("memberID"));

        TableColumn<Payment, Integer> colMaGoi = new TableColumn<>("Mã gói");
        colMaGoi.setCellValueFactory(new PropertyValueFactory<>("subscriptionID"));

        TableColumn<Payment, Integer> colMaThe = new TableColumn<>("Mã thẻ");
        colMaThe.setCellValueFactory(new PropertyValueFactory<>("cardID"));

        TableColumn<Payment, String> colNgay = new TableColumn<>("Ngày thanh toán");
        colNgay.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getPaymentDate().format(formatter)
            );
        });

        TableColumn<Payment, String> colLoai = new TableColumn<>("Loại thanh toán");
        colLoai.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.getColumns().addAll(colMa, colMaHV, colMaGoi, colMaThe, colNgay, colLoai);

        // Tạo thanh tìm kiếm
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Tìm theo mã thanh toán...");
        tfSearch.setMaxWidth(200);
        Button btnTim = new Button("Tìm kiếm");
        btnTim.setOnAction(e -> {
            String keyword = tfSearch.getText();
            if (!keyword.isEmpty()) {
                table.setItems(controller.timKiemPayment(keyword));
            } else {
                table.setItems(controller.getDanhSachPayment());
            }
        });
        
        HBox searchBox = new HBox(10, tfSearch, btnTim);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        // Thêm các thành phần vào container
        container.getChildren().addAll(
            new Label("Lịch sử thanh toán"),
            searchBox,
            table
        );

        return container;
    }
} 