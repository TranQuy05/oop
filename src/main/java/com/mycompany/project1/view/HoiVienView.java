/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.controller.HoiVienController;
import com.mycompany.project1.model.HoiVien;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ButtonType;

/**
 *
 * @author dtquy
 */
public class HoiVienView {
    private BorderPane root;
    private HoiVienController controller;
    private TableView<HoiVien> table;
    private TextField tfTen, tfNamSinh, tfSdt, tfGioiTinh, tfDiaChi, tfEmail, tfCanCuocCongDan;

    public HoiVienView(BorderPane root) {
        this.root = root;
        this.controller = new HoiVienController();
    }

    public Node showHoiVien() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        // Tạo bảng hiển thị hội viên
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(controller.getDanhSachHoiVien());

        // Thêm các cột cho bảng
        TableColumn<HoiVien, Integer> colMa = new TableColumn<>("Mã HV");
        colMa.setCellValueFactory(new PropertyValueFactory<>("maHoiVien"));

        TableColumn<HoiVien, String> colHoTen = new TableColumn<>("Họ Tên");
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));

        TableColumn<HoiVien, Integer> colNamSinh = new TableColumn<>("Năm sinh");
        colNamSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));

        TableColumn<HoiVien, String> colGioiTinh = new TableColumn<>("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));

        TableColumn<HoiVien, String> colDiaChi = new TableColumn<>("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));

        TableColumn<HoiVien, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<HoiVien, Integer> colSDT = new TableColumn<>("SĐT");
        colSDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));

        TableColumn<HoiVien, String> colCCCD = new TableColumn<>("CCCD");
        colCCCD.setCellValueFactory(new PropertyValueFactory<>("canCuocCongDan"));

        table.getColumns().addAll(colMa, colHoTen, colNamSinh, colSDT, colGioiTinh, colDiaChi, colEmail, colCCCD);

        // Tạo form nhập thông tin hội viên
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));
        form.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-radius: 5;");

        // Tạo các trường nhập liệu
        tfTen = new TextField(); tfTen.setPromptText("Họ tên");
        tfNamSinh = new TextField(); tfNamSinh.setPromptText("Năm sinh");
        tfSdt = new TextField(); tfSdt.setPromptText("SĐT");
        tfGioiTinh = new TextField(); tfGioiTinh.setPromptText("Giới tính");
        tfDiaChi = new TextField(); tfDiaChi.setPromptText("Địa chỉ");
        tfEmail = new TextField(); tfEmail.setPromptText("Email");
        tfCanCuocCongDan = new TextField(); tfCanCuocCongDan.setPromptText("Căn cước công dân");

        // Tạo các label hiển thị yêu cầu
        Label lblTenReq = new Label("(Không được để trống)");
        lblTenReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");
        
        Label lblNamSinhReq = new Label("(Năm sinh từ 1900 đến " + java.time.Year.now().getValue() + ")");
        lblNamSinhReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");
        
        Label lblSdtReq = new Label("(Phải là số dương)");
        lblSdtReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");
        
        Label lblGioiTinhReq = new Label("(Không được để trống)");
        lblGioiTinhReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");
        
        Label lblDiaChiReq = new Label("(Không được để trống)");
        lblDiaChiReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");
        
        Label lblEmailReq = new Label("(Định dạng: example@domain.com)");
        lblEmailReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");
        
        Label lblCCCDReq = new Label("(9-12 chữ số)");
        lblCCCDReq.setStyle("-fx-text-fill: #666; -fx-font-size: 11px;");

        // Thêm các trường vào form
        form.add(new Label("Họ tên:"), 0, 0);
        form.add(tfTen, 1, 0);
        form.add(lblTenReq, 2, 0);
        
        form.add(new Label("Năm sinh:"), 0, 1);
        form.add(tfNamSinh, 1, 1);
        form.add(lblNamSinhReq, 2, 1);
        
        form.add(new Label("Số điện thoại:"), 0, 2);
        form.add(tfSdt, 1, 2);
        form.add(lblSdtReq, 2, 2);
        
        form.add(new Label("Giới tính:"), 0, 3);
        form.add(tfGioiTinh, 1, 3);
        form.add(lblGioiTinhReq, 2, 3);
        
        form.add(new Label("Địa chỉ:"), 0, 4);
        form.add(tfDiaChi, 1, 4);
        form.add(lblDiaChiReq, 2, 4);
        
        form.add(new Label("Email:"), 0, 5);
        form.add(tfEmail, 1, 5);
        form.add(lblEmailReq, 2, 5);
        
        form.add(new Label("CCCD:"), 0, 6);
        form.add(tfCanCuocCongDan, 1, 6);
        form.add(lblCCCDReq, 2, 6);

        // Tạo các nút chức năng
        Button btnThem = new Button("Thêm hội viên");
        btnThem.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        
        Button btnSua = new Button("Sửa thông tin");
        btnSua.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        
        Button btnXoa = new Button("Xóa hội viên");
        btnXoa.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        Button btnHuy = new Button("Hủy");
        btnHuy.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
        btnHuy.setOnAction(e -> {
            clearInputFields();
            table.getSelectionModel().clearSelection();
        });

        HBox buttonBox = new HBox(10, btnThem, btnSua, btnXoa, btnHuy);
        buttonBox.setAlignment(Pos.CENTER);
        form.add(buttonBox, 0, 7, 3, 1);

        // Xử lý sự kiện thêm hội viên
        btnThem.setOnAction(e -> {
            if (validateAndSave()) {
                clearInputFields();
                loadData();
            }
        });

        // Xử lý sự kiện sửa thông tin
        btnSua.setOnAction(e -> {
            HoiVien selectedHoiVien = table.getSelectionModel().getSelectedItem();
            if (selectedHoiVien != null) {
                // Điền thông tin vào form
                tfTen.setText(selectedHoiVien.getHoTen());
                tfNamSinh.setText(String.valueOf(selectedHoiVien.getNamSinh()));
                tfSdt.setText(String.valueOf(selectedHoiVien.getSdt()));
                tfGioiTinh.setText(selectedHoiVien.getGioiTinh());
                tfDiaChi.setText(selectedHoiVien.getDiaChi());
                tfEmail.setText(selectedHoiVien.getEmail());
                tfCanCuocCongDan.setText(selectedHoiVien.getCanCuocCongDan());

                // Cập nhật thông tin
                if (validateAndUpdate(selectedHoiVien.getMaHoiVien())) {
                    clearInputFields();
                    loadData();
                }
            } else {
                showAlert("Thông báo", "Vui lòng chọn hội viên cần sửa!", Alert.AlertType.WARNING);
            }
        });

        // Xử lý sự kiện xóa hội viên
        btnXoa.setOnAction(e -> {
            HoiVien selectedHoiVien = table.getSelectionModel().getSelectedItem();
            if (selectedHoiVien != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Xác nhận xóa");
                confirm.setHeaderText(null);
                confirm.setContentText("Bạn có chắc chắn muốn xóa hội viên này?");
                
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        if (controller.xoaHoiVien(selectedHoiVien.getMaHoiVien())) {
                            showAlert("Thành công", "Đã xóa hội viên thành công!", Alert.AlertType.INFORMATION);
                            loadData();
                        } else {
                            showAlert("Lỗi", "Không thể xóa hội viên!", Alert.AlertType.ERROR);
                        }
                    }
                });
            } else {
                showAlert("Thông báo", "Vui lòng chọn hội viên cần xóa!", Alert.AlertType.WARNING);
            }
        });

        // Thanh tìm kiếm
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Tìm theo mã hoặc tên hội viên...");
        tfSearch.setMaxWidth(200);
        Button btnTim = new Button("Tìm kiếm");
        btnTim.setOnAction(e -> {
            String keyword = tfSearch.getText();
            if (!keyword.isEmpty()) {
                table.setItems(controller.timKiemHoiVien(keyword));
            } else {
                table.setItems(controller.getDanhSachHoiVien());
            }
        });

        HBox searchBox = new HBox(10, tfSearch, btnTim);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        // Thêm các thành phần vào container
        container.getChildren().addAll(
            new Label("Quản lý hội viên"),
            searchBox,
            form,
            new Label("Danh sách hội viên:"),
            table
        );

        return container;
    }

    private boolean validateAndSave() {
        if (controller.validateInput("0", tfTen.getText(), tfNamSinh.getText(),
                tfSdt.getText(), tfGioiTinh.getText(), tfDiaChi.getText(), 
                tfEmail.getText(), tfCanCuocCongDan.getText())) {
            
            try {
                String hoTen = tfTen.getText();
                int namSinh = Integer.parseInt(tfNamSinh.getText());
                int sdt = Integer.parseInt(tfSdt.getText());
                String gioiTinh = tfGioiTinh.getText();
                String diaChi = tfDiaChi.getText();
                String email = tfEmail.getText();
                String canCuocCongDan = tfCanCuocCongDan.getText();

                if (controller.themHoiVien(0, hoTen, namSinh, sdt, gioiTinh, diaChi, email, canCuocCongDan)) {
                    showAlert("Thành công", "Thêm hội viên thành công!", Alert.AlertType.INFORMATION);
                    return true;
                } else {
                    showAlert("Lỗi", "Thêm hội viên thất bại!", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException ex) {
                showAlert("Lỗi", "Vui lòng nhập đúng định dạng số cho năm sinh và số điện thoại!", Alert.AlertType.ERROR);
            }
        }
        return false;
    }

    private boolean validateAndUpdate(int maHV) {
        if (controller.validateInput(String.valueOf(maHV), tfTen.getText(), tfNamSinh.getText(),
                tfSdt.getText(), tfGioiTinh.getText(), tfDiaChi.getText(), 
                tfEmail.getText(), tfCanCuocCongDan.getText())) {
            
            try {
                String hoTen = tfTen.getText();
                int namSinh = Integer.parseInt(tfNamSinh.getText());
                int sdt = Integer.parseInt(tfSdt.getText());
                String gioiTinh = tfGioiTinh.getText();
                String diaChi = tfDiaChi.getText();
                String email = tfEmail.getText();
                String canCuocCongDan = tfCanCuocCongDan.getText();

                if (controller.capNhatHoiVien(maHV, hoTen, namSinh, sdt, gioiTinh, diaChi, email, canCuocCongDan)) {
                    showAlert("Thành công", "Cập nhật thông tin thành công!", Alert.AlertType.INFORMATION);
                    return true;
                } else {
                    showAlert("Lỗi", "Cập nhật thông tin thất bại!", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException ex) {
                showAlert("Lỗi", "Vui lòng nhập đúng định dạng số cho năm sinh và số điện thoại!", Alert.AlertType.ERROR);
            }
        }
        return false;
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearInputFields() {
        tfTen.clear();
        tfNamSinh.clear();
        tfSdt.clear();
        tfGioiTinh.clear();
        tfDiaChi.clear();
        tfEmail.clear();
        tfCanCuocCongDan.clear();
    }

    private void loadData() {
        table.setItems(controller.getDanhSachHoiVien());
    }
}



