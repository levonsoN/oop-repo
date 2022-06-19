package com.example.untitled;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class MainForm {
    private final Calculator calculator = new Calculator();
    @FXML
    private TextArea textField;
    @FXML
    private Spinner<Double> aSpinner;
    @FXML
    private Spinner<Double> bSpinner;
    @FXML
    private Spinner<Double> hSpinner;
    @FXML
    private Button calcButton;

    @FXML
    private void initialize() {
        aSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, 1, 0));
        bSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 1));
        hSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.1));
        aSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                bSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(newValue, Double.MAX_VALUE, bSpinner.getValue())));
        bSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                aSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, newValue, aSpinner.getValue())));
    }

    @FXML
    private void calculateButton() {
		textField.setText("");
        Point2D[] points = calculator.tabulate(aSpinner.getValue(), bSpinner.getValue(), hSpinner.getValue());
        for (int i = 0; i < points.length; i++)
            textField.appendText(String.format("x=%.4f y=%.4f\n", points[i].getX(), points[i].getY()));
        Point2D min = calculator.getMin(points);
        Point2D max = calculator.getMax(points);
        double sum = calculator.sum(points);
        double avg = calculator.avg(points);
        textField.appendText(String.format("x_min=%.4f y_min=%.4f\n", min.getX(), min.getY()));
        textField.appendText(String.format("x_max=%.4f y_max=%.4f\n", max.getX(), max.getY()));
        textField.appendText(String.format("sum=%.4f\n", sum));
        textField.appendText(String.format("avg=%.4f\n", avg));
    }


}
