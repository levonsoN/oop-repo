package com.lvn.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class MainForm {
    @FXML
    private TextArea textField;
    @FXML
    private Spinner<Double> aSpinner;
    @FXML
    private Spinner<Double> bSpinner;
    @FXML
    private Spinner<Integer> hSpinner;
    @FXML
    private Spinner<Integer> tSpinner;
    @FXML
    private Button calcButton;

    @FXML
    private void initialize() {
        aSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, 1, 0));
        bSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, Math.PI / 2));
        hSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1));
        tSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));

        aSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                bSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(newValue, Double.MAX_VALUE, bSpinner.getValue())));
        bSpinner.valueProperty().addListener((observable, oldValue, newValue) ->
                aSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, newValue, aSpinner.getValue())));
    }

    @FXML
    private void calculateButton() throws InterruptedException {
        textField.setText("");
        long startTime = System.nanoTime();
        double a = aSpinner.getValue();
        double b = bSpinner.getValue();
        int h = hSpinner.getValue();
        int t = tSpinner.getValue();
        if (h < t) t = h;
        double stepLength = (b - a) / h;
        int stepsPerThread = (int) Math.round((double) h / t);
        Thread[] threads = new Thread[t];
        Integrator[] integrators = new Integrator[t];
        MyFunction function = new MyFunction();
        for (int i = 0; i < t; i++) {
            double localA = a + i * stepLength * stepsPerThread;
            double localB = a + (i + 1) * stepLength * stepsPerThread - stepLength;
            if (i == t - 1 && localB < b) localB = b;
            integrators[i] = new Integrator(function, localA, localB, stepLength);
            threads[i] = new Thread(integrators[i]);
            threads[i].start();
        }
        double result = 0;
        for (int i = 0; i < t; i++) {
            threads[i].join();
            result += integrators[i].getSum();
            textField.appendText(String.format("Partial result = %.4f\n", integrators[i].getSum()));
        }
        long endTime = System.nanoTime();
        textField.appendText(String.format("Result = %.4f\n", result));
        textField.appendText("Time elapsed: " + (endTime - startTime) / 1000 + "ms.\n");
    }
}
