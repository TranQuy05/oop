///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.project1.controller;
//
//import java.time.LocalDate;
//import java.time.Period;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
///**
// *
// * @author dtquy
// */
//public class DatePicker extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        DatePicker datePickerFrom = new DatePicker();
//        DatePicker datePickerTo = new DatePicker();
//
//        Label resultLabel = new Label();
//        Button btnTinhKhoangThoiGian = new Button("khoảng thời gian");
//
//        btnTinhKhoangThoiGian.setOnAction(e -> {
//            LocalDate from = datePickerFrom.getValue();
//            LocalDate to = datePickerTo.getValue();
//
//            if (from != null && to != null && !to.isBefore(from)) {
//                Period period = Period.between(from, to);
//                resultLabel.setText("Khoảng thời gian: " + 
//                    period.getYears() + " năm, " + 
//                    period.getMonths() + " tháng, " + 
//                    period.getDays() + " ngày");
//            } else {
//                resultLabel.setText("Vui lòng chọn khoảng thời gian hợp lệ!");
//            }
//        });
//
//        VBox root = new VBox(10, new Label("Từ ngày:"), datePickerFrom, new Label("Đến ngày:"), datePickerTo, btnTinhKhoangThoiGian, resultLabel);
//
//        root.setStyle("-fx-padding: 20;");
//        Scene scene = new Scene(root, 350, 250);
//        primaryStage.setTitle("Chọn khoảng thời gian");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
