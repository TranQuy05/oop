module com.mycompany.project1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.project1 to javafx.fxml;
    exports com.mycompany.project1;
}
