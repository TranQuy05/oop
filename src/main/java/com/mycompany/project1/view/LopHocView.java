/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.controller.LopHocController;
import com.mycompany.project1.model.LopHoc;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author dtquy
 */
public class LopHocView {
    private BorderPane root;
    private LopHocController controller;
    private TableView<LopHoc> table;

    public LopHocView(BorderPane root) {
        this.root = root;
        this.controller = new LopHocController();
    }

    public Node showLopHoc() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));

        // Tiêu đề
        Label title = new Label("Quản lý lớp học");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Form thêm lớp học
        VBox formBox = createFormSection();
        
        // Bảng hiển thị lớp học
        VBox tableBox = createTableSection();

        mainContent.getChildren().addAll(title, formBox, tableBox);

        // Wrap trong ScrollPane
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: white; -fx-border-color: white;");

        return scrollPane;
    }

    private VBox createFormSection() {
        VBox formBox = new VBox(10);
        formBox.setPadding(new Insets(20));
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label formTitle = new Label("Thêm lớp học mới");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Các trường nhập liệu
        TextField tfTenLop = new TextField();
        tfTenLop.setPromptText("Tên lớp học");
        
        ComboBox<String> cbHuongDanVien = new ComboBox<>();
        cbHuongDanVien.setPromptText("Chọn huấn luyện viên");
        cbHuongDanVien.setItems(controller.getDanhSachHuongDanVien());
        
        TextField tfLichHoc = new TextField();
        tfLichHoc.setPromptText("Lịch học (VD: Thứ 2,4,6 - 18:00-19:30)");

        Button btnThem = new Button("Thêm lớp học");
        btnThem.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        
        btnThem.setOnAction(e -> {
            if (validateAndSave(tfTenLop.getText(), cbHuongDanVien.getValue(), tfLichHoc.getText())) {
                clearForm(tfTenLop, cbHuongDanVien, tfLichHoc);
                refreshTable();
            }
        });

        formBox.getChildren().addAll(formTitle, tfTenLop, cbHuongDanVien, tfLichHoc, btnThem);
        return formBox;
    }

    private VBox createTableSection() {
        VBox tableBox = new VBox(10);
        tableBox.setPadding(new Insets(20));
        tableBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label tableTitle = new Label("Danh sách lớp học");
        tableTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Tạo bảng
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<LopHoc, Integer> colMa = new TableColumn<>("Mã lớp");
        colMa.setCellValueFactory(new PropertyValueFactory<>("classID"));

        TableColumn<LopHoc, String> colTen = new TableColumn<>("Tên lớp");
        colTen.setCellValueFactory(new PropertyValueFactory<>("className"));

        TableColumn<LopHoc, String> colHLV = new TableColumn<>("Huấn luyện viên");
        colHLV.setCellValueFactory(new PropertyValueFactory<>("trainerName"));

        TableColumn<LopHoc, String> colLich = new TableColumn<>("Lịch học");
        colLich.setCellValueFactory(new PropertyValueFactory<>("schedule"));

        // Thêm nút xóa và sửa
        TableColumn<LopHoc, Void> colAction = new TableColumn<>("Thao tác");
        colAction.setCellFactory(col -> new TableCell<>() {
            private final Button btnXoa = new Button("Xóa");
            private final Button btnSua = new Button("Sửa");
            {
                btnXoa.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                btnSua.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                
                btnXoa.setOnAction(e -> {
                    LopHoc lop = getTableView().getItems().get(getIndex());
                    if (confirmDelete()) {
                        controller.xoaLopHoc(lop.getClassID());
                        refreshTable();
                    }
                });
                
                btnSua.setOnAction(e -> {
                    LopHoc lop = getTableView().getItems().get(getIndex());
                    showEditDialog(lop);
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

        table.getColumns().addAll(colMa, colTen, colHLV, colLich, colAction);
        
        // Load dữ liệu
        refreshTable();

        tableBox.getChildren().addAll(tableTitle, table);
        return tableBox;
    }

    private boolean validateAndSave(String tenLop, String huongDanVien, String lichHoc) {
        if (tenLop.isEmpty() || huongDanVien == null || lichHoc.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin!", Alert.AlertType.ERROR);
            return false;
        }

        // Lấy trainerID từ tên huấn luyện viên
        int trainerID = getTrainerIDFromName(huongDanVien);
        if (trainerID == -1) {
            showAlert("Lỗi", "Không tìm thấy huấn luyện viên!", Alert.AlertType.ERROR);
            return false;
        }

        if (controller.themLopHoc(tenLop, trainerID, lichHoc)) {
            showAlert("Thành công", "Thêm lớp học thành công!", Alert.AlertType.INFORMATION);
            return true;
        } else {
            showAlert("Lỗi", "Thêm lớp học thất bại!", Alert.AlertType.ERROR);
            return false;
        }
    }

    private int getTrainerIDFromName(String trainerName) {
        return controller.getTrainerIDFromName(trainerName);
    }

    private void clearForm(TextField tfTenLop, ComboBox<String> cbHuongDanVien, TextField tfLichHoc) {
        tfTenLop.clear();
        cbHuongDanVien.setValue(null);
        tfLichHoc.clear();
    }

    private void refreshTable() {
        table.setItems(controller.getDanhSachLopHoc());
    }

    private boolean confirmDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xóa lớp học này?");
        return alert.showAndWait().get() == ButtonType.OK;
    }

    private void showEditDialog(LopHoc lop) {
        Dialog<LopHoc> dialog = new Dialog<>();
        dialog.setTitle("Sửa thông tin lớp học");
        dialog.setHeaderText(null);

        // Tạo form
        TextField tfTenLop = new TextField(lop.getClassName());
        tfTenLop.setPromptText("Tên lớp học");
        
        ComboBox<String> cbHuongDanVien = new ComboBox<>();
        cbHuongDanVien.setPromptText("Chọn huấn luyện viên");
        cbHuongDanVien.setItems(controller.getDanhSachHuongDanVien());
        cbHuongDanVien.setValue(lop.getTrainerName());
        
        TextField tfLichHoc = new TextField(lop.getSchedule());
        tfLichHoc.setPromptText("Lịch học");

        dialog.getDialogPane().setContent(new VBox(10, 
            new Label("Tên lớp:"), tfTenLop,
            new Label("Huấn luyện viên:"), cbHuongDanVien,
            new Label("Lịch học:"), tfLichHoc
        ));

        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                lop.setClassName(tfTenLop.getText());
                lop.setTrainerName(cbHuongDanVien.getValue());
                lop.setSchedule(tfLichHoc.getText());
                return lop;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(updatedLop -> {
            if (controller.capNhatLopHoc(updatedLop)) {
                showAlert("Thành công", "Cập nhật lớp học thành công!", Alert.AlertType.INFORMATION);
                refreshTable();
            } else {
                showAlert("Lỗi", "Cập nhật lớp học thất bại!", Alert.AlertType.ERROR);
            }
        });
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
