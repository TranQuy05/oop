package com.mycompany.project1.view;

import com.mycompany.project1.controller.NhanVienController;
import com.mycompany.project1.model.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.geometry.Pos;
import com.mycompany.project1.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;

public class NhanVienView {
    private BorderPane root;
    private NhanVienController controller;

    public NhanVienView(BorderPane root) {
        this.root = root;
        this.controller = new NhanVienController();
    }

    public Node showEmployeePage() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        TableView<NhanVien> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(controller.getDanhSachNhanVien());

        TableColumn<NhanVien, Integer> colMa = new TableColumn<>("Mã NV");
        colMa.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getStaffID()).asObject());

        TableColumn<NhanVien, String> colHoTen = new TableColumn<>("Họ Tên");
        colHoTen.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getUserName()));

        TableColumn<NhanVien, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getUserEmail()));

        TableColumn<NhanVien, String> colRole = new TableColumn<>("Vai trò");
        colRole.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getRole()));

        TableColumn<NhanVien, Integer> colNamSinh = new TableColumn<>("Năm sinh");
        colNamSinh.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getUser().getUserYoB()).asObject());

        TableColumn<NhanVien, String> colGioiTinh = new TableColumn<>("Giới tính");
        colGioiTinh.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getGender()));

        TableColumn<NhanVien, String> colDiaChi = new TableColumn<>("Địa chỉ");
        colDiaChi.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getAddress()));

        TableColumn<NhanVien, String> colSDT = new TableColumn<>("SĐT");
        colSDT.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getPhoneNum()));

        TableColumn<NhanVien, String> colCCCD = new TableColumn<>("CCCD");
        colCCCD.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getCitizenIdentification()));

        table.getColumns().addAll(colMa, colHoTen, colEmail, colRole, colNamSinh, 
                                colGioiTinh, colDiaChi, colSDT, colCCCD);

        // Tạo form nhập thông tin nhân viên
        TextField tfTen = new TextField(); tfTen.setPromptText("Họ tên");
        TextField tfEmail = new TextField(); tfEmail.setPromptText("Email");
        TextField tfPassword = new TextField(); tfPassword.setPromptText("Mật khẩu");
        TextField tfRole = new TextField(); tfRole.setPromptText("Vai trò");
        TextField tfNamSinh = new TextField(); tfNamSinh.setPromptText("Năm sinh");
        TextField tfGioiTinh = new TextField(); tfGioiTinh.setPromptText("Giới tính");
        TextField tfDiaChi = new TextField(); tfDiaChi.setPromptText("Địa chỉ");
        TextField tfSDT = new TextField(); tfSDT.setPromptText("Số điện thoại");
        TextField tfCCCD = new TextField(); tfCCCD.setPromptText("CCCD");

        HBox form = new HBox(10, tfTen, tfEmail, tfPassword, tfRole, 
                           tfNamSinh, tfGioiTinh, tfDiaChi, tfSDT, tfCCCD);
        form.setAlignment(Pos.CENTER);
        
        VBox formSection = new VBox(10, new Label("Nhập thông tin nhân viên:"), form);

        // Nút để toggle hiển thị form
        Button btnThem = new Button("Thêm nhân viên");
        btnThem.setOnAction(e -> {
            if (container.getChildren().contains(formSection)) {
                container.getChildren().remove(formSection);
                btnThem.setText("➕ Thêm nhân viên");
            } else {
                container.getChildren().add(formSection);
                btnThem.setText("✖ Ẩn form");
            }
        });

        // Thêm nút lưu thông tin nhân viên
        Button btnLuu = new Button("Lưu thông tin");
        btnLuu.setOnAction(e -> {
            try {
                String hoTen = tfTen.getText();
                String email = tfEmail.getText();
                String password = tfPassword.getText();
                String role = tfRole.getText();
                int namSinh = Integer.parseInt(tfNamSinh.getText());
                String gioiTinh = tfGioiTinh.getText();
                String diaChi = tfDiaChi.getText();
                String sdt = tfSDT.getText();
                String cccd = tfCCCD.getText();

                User user = new User(0, hoTen, email, password, role, namSinh, gioiTinh, diaChi, sdt, cccd);
                NhanVien nhanVienMoi = new NhanVien(0, "2024-01-01", user);

                if (controller.themNhanVien(nhanVienMoi)) {
                    // Clear form fields
                    tfTen.clear();
                    tfEmail.clear();
                    tfPassword.clear();
                    tfRole.clear();
                    tfNamSinh.clear();
                    tfGioiTinh.clear();
                    tfDiaChi.clear();
                    tfSDT.clear();
                    tfCCCD.clear();
                    
                    // Hide form after adding
                    container.getChildren().remove(formSection);
                    btnThem.setText("➕ Thêm nhân viên");
                    
                    // Hiển thị alert thành công với mã nhân viên mới
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm nhân viên thành công!\nMã nhân viên mới: " + nhanVienMoi.getUser().getUserID());
                    alert.showAndWait();
                    
                    // Refresh table để hiển thị nhân viên mới
                    table.setItems(controller.getDanhSachNhanVien());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm nhân viên thất bại! Vui lòng kiểm tra lại thông tin.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đúng định dạng số cho năm sinh");
                alert.showAndWait();
            }
        });

        
        form.getChildren().add(btnLuu);
        
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Tìm theo mã nhân viên...");
        tfSearch.setMaxWidth(200);
        Button btnTim = new Button("Tìm kiếm");
        btnTim.setOnAction(e -> {
            String keyword = tfSearch.getText();
            if (!keyword.isEmpty()) {
                table.setItems(controller.timKiemNhanVien(keyword));
            } else {
                table.setItems(controller.getDanhSachNhanVien());
            }
        });
        
        HBox thanhTimKiem = new HBox(10, tfSearch, btnTim, btnThem);
        
        // Giao diện chính
        container.getChildren().addAll(
            new Label("Danh sách nhân viên:"),
            thanhTimKiem,
            table
        );

        return container;
    }
}
