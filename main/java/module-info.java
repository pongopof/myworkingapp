module com.example.appindev {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.appindev to javafx.fxml;
    exports com.example.appindev;
}