module com.example.untitled {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lvn.lab10 to javafx.fxml;
    exports com.lvn.lab10;
}