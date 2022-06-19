module com.example.untitled {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.untitled to javafx.fxml;
    exports com.example.untitled;
}