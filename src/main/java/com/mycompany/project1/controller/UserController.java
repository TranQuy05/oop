package com.mycompany.project1.controller;

import com.mycompany.project1.model.User;
import com.mycompany.project1.DAO.UserDAO;
import javafx.collections.ObservableList;

public class UserController {
    private UserDAO dao;

    public UserController() {
        this.dao = new UserDAO();
    }

    public ObservableList<User> getDanhSachUser() {
        return dao.getDanhSachUser();
    }

    public User getUserByID(int userID) {
        return dao.getUserByID(userID);
    }

    public boolean themUser(User user) {
        return dao.themUser(user);
    }

    public boolean capNhatUser(User user) {
        return dao.capNhatUser(user);
    }

    public boolean xoaUser(int userID) {
        return dao.xoaUser(userID);
    }

    public User login(String email, String password) {
        return dao.login(email, password);
    }
} 