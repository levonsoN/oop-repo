module com.lvn.plotter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lvn.plotter to javafx.fxml;
    exports com.lvn.plotter;
}