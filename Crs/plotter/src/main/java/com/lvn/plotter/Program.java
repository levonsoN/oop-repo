package com.lvn.plotter;

import com.lvn.plotter.mathParser.parser.MathExpression;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(root);
        stage.setMinWidth(616.0);
        stage.setMinHeight(438);
        stage.setScene(scene);
        stage.setTitle("Math Plotter");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}