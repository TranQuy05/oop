package com.mycompany.project1.view;

import com.mycompany.project1.controller.GoiDangKyController;
import com.mycompany.project1.model.GoiDangKy;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.beans.property.SimpleStringProperty;
import java.time.format.DateTimeFormatter;

public class GoiDangKyView {
    private BorderPane root;
    private GoiDangKyController controller;
    private TableView<GoiDangKy> table;

    public GoiDangKyView(BorderPane root) {
        this.root = root;
        this.controller = new GoiDangKyController();
    }

    public Node showGoiDangKy() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        // Tạo bảng hiển thị
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(controller.getDanhSachGoiDangKy());

        // Add columns
        TableColumn<GoiDangKy, Integer> colMa = new TableColumn<>("Mã gói");
        colMa.setCellValueFactory(new PropertyValueFactory<>("subscriptionID"));

        TableColumn<GoiDangKy, String> colTen = new TableColumn<>("Tên gói");
        colTen.setCellValueFactory(new PropertyValueFactory<>("subName"));

        TableColumn<GoiDangKy, String> colLoai = new TableColumn<>("Loại gói");
        colLoai.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<GoiDangKy, String> colNgay = new TableColumn<>("Ngày bắt đầu");
        colNgay.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new SimpleStringProperty(cellData.getValue().getStartDate().format(formatter));
        });

        TableColumn<GoiDangKy, String> colChiTiet = new TableColumn<>("Chi tiết");
        colChiTiet.setCellValueFactory(new PropertyValueFactory<>("subscriptionDetail"));

        TableColumn<GoiDangKy, String> colTrangThai = new TableColumn<>("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Thêm nút xóa và sửa
        TableColumn<GoiDangKy, Void> colAction = new TableColumn<>("Thao tác");
        colAction.setCellFactory(col -> new TableCell<>() {
            private final Button btnXoa = new Button("Xóa");
            private final Button btnSua = new Button("Sửa");
            {
                btnXoa.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                btnSua.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                
                btnXoa.setOnAction(e -> {
                    GoiDangKy goi = getTableView().getItems().get(getIndex());
                    if (confirmDelete()) {
                        controller.xoaGoiDangKy(goi.getSubscriptionID());
                        refreshTable();
                    }
                });
                
                btnSua.setOnAction(e -> {
                    GoiDangKy goi = getTableView().getItems().get(getIndex());
                    showEditDialog(goi);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(5, btnSua, btnXoa);
                    buttons.setAlignment(Pos.CENTER);
                    setGraphic(buttons);
                }
            }
        });

        table.getColumns().addAll(colMa, colTen, colLoai, colNgay, colChiTiet, colTrangThai, colAction);

        // Tạo form thêm mới
        VBox formBox = createFormSection();
        
        // Tạo thanh tìm kiếm
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Tìm theo mã gói...");
        tfSearch.setMaxWidth(200);
        Button btnTim = new Button("Tìm kiếm");
        btnTim.setOnAction(e -> {
            String keyword = tfSearch.getText();
            if (!keyword.isEmpty()) {
                table.setItems(controller.timKiemGoiDangKy(keyword));
            } else {
                refreshTable();
            }
        });
        
        HBox searchBox = new HBox(10, tfSearch, btnTim);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        // Thêm các thành phần vào container
        container.getChildren().addAll(
            new Label("Quản lý gói đăng ký"),
            searchBox,
            formBox,
            table
        );

        return container;
    }

    private VBox createFormSection() {
        VBox formBox = new VBox(10);
        formBox.setPadding(new Insets(20));
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        TextField tfTen = new TextField();
        tfTen.setPromptText("Tên gói");
        
        TextField tfLoai = new TextField();
        tfLoai.setPromptText("Loại gói");
        
        DatePicker dpNgay = new DatePicker();
        dpNgay.setPromptText("Ngày bắt đầu");
        
        TextArea taChiTiet = new TextArea();
        taChiTiet.setPromptText("Chi tiết gói");
        taChiTiet.setPrefRowCount(3);
        
        ComboBox<String> cbTrangThai = new ComboBox<>();
        cbTrangThai.getItems().addAll("Active", "Inactive");
        cbTrangThai.setPromptText("Trạng thái");

        Button btnThem = new Button("Thêm gói mới");
        btnThem.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        
        btnThem.setOnAction(e -> {
            if (validateForm(tfTen, tfLoai, dpNgay, taChiTiet, cbTrangThai)) {
                try {
                    GoiDangKy goi = new GoiDangKy();
                    goi.setSubName(tfTen.getText());
                    goi.setType(tfLoai.getText());
                    goi.setStartDate(dpNgay.getValue());
                    goi.setSubscriptionDetail(taChiTiet.getText());
                    goi.setStatus(cbTrangThai.getValue());

                    if (controller.themGoiDangKy(goi)) {
                        clearForm(tfTen, tfLoai, dpNgay, taChiTiet, cbTrangThai);
                        refreshTable();
                        showAlert("Thành công", "Thêm gói đăng ký mới thành công!\nMã gói mới: " + goi.getSubscriptionID(), 
                                 Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Lỗi", "Thêm gói đăng ký thất bại!", Alert.AlertType.ERROR);
                    }
                } catch (Exception ex) {
                    showAlert("Lỗi", "Có lỗi xảy ra: " + ex.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });

        formBox.getChildren().addAll(
            new Label("Thêm gói đăng ký mới"),
            tfTen, tfLoai, dpNgay, taChiTiet, cbTrangThai, btnThem
        );

        return formBox;
    }

    private void clearForm(TextField tfTen, TextField tfLoai,  
                          DatePicker dpNgay, TextArea taChiTiet, ComboBox<String> cbTrangThai) {
        tfTen.clear();
        tfLoai.clear();
        dpNgay.setValue(null);
        taChiTiet.clear();
        cbTrangThai.setValue(null);
    }

    private void refreshTable() {
        table.setItems(controller.getDanhSachGoiDangKy());
    }

    private boolean confirmDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xóa gói đăng ký này?");
        return alert.showAndWait().get() == ButtonType.OK;
    }

    private void showEditDialog(GoiDangKy goi) {
        Dialog<GoiDangKy> dialog = new Dialog<>();
        dialog.setTitle("Sửa thông tin gói đăng ký");
        dialog.setHeaderText(null);

        TextField tfTen = new TextField(goi.getSubName());
        TextField tfLoai = new TextField(goi.getType());
        DatePicker dpNgay = new DatePicker(goi.getStartDate());
        TextArea taChiTiet = new TextArea(goi.getSubscriptionDetail());
        ComboBox<String> cbTrangThai = new ComboBox<>();
        cbTrangThai.getItems().addAll("Active", "Inactive");
        cbTrangThai.setValue(goi.getStatus());

        dialog.getDialogPane().setContent(new VBox(10, 
            new Label("Tên gói:"), tfTen,
            new Label("Loại:"), tfLoai,
            new Label("Ngày bắt đầu:"), dpNgay,
            new Label("Chi tiết:"), taChiTiet,
            new Label("Trạng thái:"), cbTrangThai
        ));

        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                goi.setSubName(tfTen.getText());
                goi.setType(tfLoai.getText());
                goi.setStartDate(dpNgay.getValue());
                goi.setSubscriptionDetail(taChiTiet.getText());
                goi.setStatus(cbTrangThai.getValue());
                return goi;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(updatedGoi -> {
            if (controller.capNhatGoiDangKy(updatedGoi)) {
                showAlert("Thành công", "Cập nhật gói đăng ký thành công!", Alert.AlertType.INFORMATION);
                refreshTable();
            } else {
                showAlert("Lỗi", "Cập nhật gói đăng ký thất bại!", Alert.AlertType.ERROR);
            }
        });
    }

    private boolean validateForm(TextField tfTen, TextField tfLoai, DatePicker dpNgay, 
                               TextArea taChiTiet, ComboBox<String> cbTrangThai) {
        if (tfTen.getText().isEmpty() || tfLoai.getText().isEmpty() || 
            dpNgay.getValue() == null || taChiTiet.getText().isEmpty() || 
            cbTrangThai.getValue() == null) {
            showAlert("Lỗi", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 