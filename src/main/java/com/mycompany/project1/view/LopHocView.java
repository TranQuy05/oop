/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;


import com.mycompany.project1.model.LopHoc;
import java.time.LocalDate;
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

/**
 *
 * @author dtquy
 */
public class LopHocView {
    private BorderPane root;

    public LopHocView(BorderPane root) {
        this.root = root;
    }

    public Node showLopHoc() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        TableView<LopHoc> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<LopHoc, Integer> colMa = new TableColumn<>("Mã lớp");
        TableColumn<LopHoc, String> colTen = new TableColumn<>("Tên lớp");
        TableColumn<LopHoc, String> colKhoaHoc = new TableColumn<>("Khóa học");
        TableColumn<LopHoc, String> colGiaoVien = new TableColumn<>("Giáo viên");
        TableColumn<LopHoc, String> colNgayHoc = new TableColumn<>("Ngày học");
        TableColumn<LopHoc, String> colGioHoc = new TableColumn<>("Giờ học");

        colMa.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        colTen.setCellValueFactory(new PropertyValueFactory<>("tenLop"));
        colKhoaHoc.setCellValueFactory(new PropertyValueFactory<>("khoaHoc"));
        colGiaoVien.setCellValueFactory(new PropertyValueFactory<>("giaoVien"));
        colNgayHoc.setCellValueFactory(new PropertyValueFactory<>("ngayHoc"));
        colGioHoc.setCellValueFactory(new PropertyValueFactory<>("gioHoc"));

        table.getColumns().addAll(colMa, colTen, colKhoaHoc, colGiaoVien, colNgayHoc, colGioHoc);

        // Form nhập thông tin lớp học
        TextField tfMa = new TextField(); tfMa.setPromptText("Mã lớp");
        TextField tfTen = new TextField(); tfTen.setPromptText("Tên lớp");
        TextField tfKhoaHoc = new TextField(); tfKhoaHoc.setPromptText("Khóa học");
        TextField tfGiaoVien = new TextField(); tfGiaoVien.setPromptText("Giáo viên");
        TextField tfNgayHoc = new TextField(); tfNgayHoc.setPromptText("Ngày học");
        TextField tfGioHoc = new TextField(); tfGioHoc.setPromptText("Giờ học");

        HBox form = new HBox(10, tfMa, tfTen, tfKhoaHoc, tfGiaoVien, tfNgayHoc, tfGioHoc);
        form.setAlignment(Pos.CENTER);

        VBox formSection = new VBox(10, new Label("Nhập thông tin lớp học:"), form);

        // Nút để toggle hiển thị form
        Button btnThem = new Button("Thêm lớp");
        btnThem.setOnAction(e -> {
            if (container.getChildren().contains(formSection)) {
                container.getChildren().remove(formSection);
                btnThem.setText("➕ Thêm lớp");
            } else {
                container.getChildren().add(formSection);
                btnThem.setText("✖ Ẩn form");
            }
        });

        // Giao diện chính
        container.getChildren().addAll(
            new Label("Danh sách lớp học:"),
            table,
            btnThem
        );

        return container;
    }
}
