module com.mycompany.project1 {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    exports com.mycompany.project1;
    exports com.mycompany.project1.DAO;
    exports com.mycompany.project1.model;
}
