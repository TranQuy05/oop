/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.model.GoiDangKy;
import com.mycompany.project1.model.NhanVien;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author dtquy
 */
public class KhoaHocView {
        private BorderPane root;
        public KhoaHocView(BorderPane root) {
        this.root = root;
    }
        public Node showKhoaHoc() {
            VBox container = new VBox(10);
            container.setPadding(new Insets(20));
            
            TableView<GoiDangKy> table = new TableView<>();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<GoiDangKy, Integer> colMa = new TableColumn<>("Mã gói");
            TableColumn<GoiDangKy, String> colLoai = new TableColumn<>("Loại gói");
            TableColumn<GoiDangKy, String> colGia = new TableColumn<>("Giá");
            TableColumn<GoiDangKy, String> colThoiGian = new TableColumn<>("Thời hạn");

            colMa.setCellValueFactory(new PropertyValueFactory<>("SubsID"));
            colLoai.setCellValueFactory(new PropertyValueFactory<>("Type"));
            colGia.setCellValueFactory(new PropertyValueFactory<>("Price"));
            colThoiGian.setCellValueFactory(new PropertyValueFactory<>("duration"));

            table.getColumns().addAll(colMa, colLoai, colGia, colThoiGian);

            // Form nhập thông tin khóa học
            TextField tfMa = new TextField(); tfMa.setPromptText("Mã khóa học");
            TextField tfTen = new TextField(); tfTen.setPromptText("Tên khóa học");
            TextField tfGiaoVien = new TextField(); tfGiaoVien.setPromptText("Giáo viên");
            TextField tfNgayBatDau = new TextField(); tfNgayBatDau.setPromptText("YYYY-MM-DD");
            TextField tfNgayKetThuc = new TextField(); tfNgayKetThuc.setPromptText("YYYY-MM-DD");
            TextField tfHocPhi = new TextField(); tfHocPhi.setPromptText("Học phí");

            HBox form = new HBox(10, tfMa, tfTen, tfGiaoVien, tfNgayBatDau, tfNgayKetThuc, tfHocPhi);
            form.setAlignment(Pos.CENTER);

            VBox formSection = new VBox(10, new Label("Nhập thông tin khóa học:"), form);

            // Nút để toggle hiển thị form
            Button btnThem = new Button("Thêm khóa học");
            btnThem.setOnAction(e -> {
                if (container.getChildren().contains(formSection)) {
                    container.getChildren().remove(formSection);
                    btnThem.setText("➕ Thêm khóa học");
                } else {
                    container.getChildren().add(formSection);
                    btnThem.setText("✖ Ẩn form");
                }
            });

            // Giao diện chính
            container.getChildren().addAll(
                new Label("Danh sách gói đăng ký:"),
                table                
            );

            return container;
        }
    }
    

