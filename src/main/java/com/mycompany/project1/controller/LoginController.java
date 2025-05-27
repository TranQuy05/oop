package com.mycompany.project1.controller;

import com.mycompany.project1.DAO.UserDAO;
import com.mycompany.project1.model.User;
import com.mycompany.project1.view.mainPage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class LoginController {
    private UserDAO userDAO;
    private User currentUser;
    
    public LoginController() {
        this.userDAO = new UserDAO();
    }
    
    public boolean authenticate(String username, String password) {
        User user = userDAO.login(username, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }
    
    public User getUser() {
        return currentUser;
    }
    
    public void showMainPage() {
        mainPage mainPage = new mainPage();
        Parent root = mainPage.showMainPage();
        Scene scene = new Scene(root);
        Stage mainStage = new Stage();
        mainStage.setTitle("Quản lý phòng gym");
        mainStage.setScene(scene);
        mainStage.setMaximized(true);
        mainStage.show();
    }
}
 