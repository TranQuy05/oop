/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.model.KhoaHoc;
import com.mycompany.project1.model.NhanVien;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
        public void showKhoaHoc(){
            VBox container = new VBox(10);
        container.setPadding(new Insets(20));
        
        TableView<KhoaHoc> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<KhoaHoc, Integer> colID = new TableColumn<>("Mã Khoá học");
        colID.setCellValueFactory(new PropertyValueFactory<>("subsID"));

        TableColumn<KhoaHoc, String> colType = new TableColumn<>("Khóa học");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<KhoaHoc, String> colPrice = new TableColumn<>("Giá khóa học");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<KhoaHoc, LocalDate> colDuration = new TableColumn<>("Thời gian diễn ra");
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        
        table.getColumns().addAll(colID, colType, colPrice, colDuration);



        container.getChildren().add(table);
        root.setCenter(container);
    }
    }
    

