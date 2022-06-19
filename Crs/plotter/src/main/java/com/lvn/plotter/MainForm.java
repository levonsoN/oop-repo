package com.lvn.plotter;

import com.lvn.plotter.mathParser.parser.MathExpression;
import com.lvn.plotter.mathParser.parser.MathParser;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainForm {
    private final MathParser parser;
    private MathExpression function;
    private boolean isFunctionChanged = true;
    @FXML
    private TextField functionTextBox;

    @FXML
    private Button plotButton;

    @FXML
    private Canvas plotCanvas;

    @FXML
    private AnchorPane root;

    @FXML
    private Spinner<Double> xOriginSpinner;

    @FXML
    private Spinner<Double> xStepSpinner;

    @FXML
    private Spinner<Double> yOriginSpinner;

    @FXML
    private Spinner<Double> yStepSpinner;

    public MainForm() throws Exception {
        DefaultTokensDefinitionBuilder builder = new DefaultTokensDefinitionBuilder();
        parser = new MathParser(new DefaultTokensDefinitionBuilder().getDefinition());
    }

    @FXML
    private void initialize() {
        clearCanvas();

        root.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            plotCanvas.setWidth(newValue.doubleValue() - 20);
            clearCanvas();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            plotCanvas.setHeight(newValue.doubleValue() - 120);
            clearCanvas();
        });
        SpinnerValueFactory<Double> xScaleValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_NORMAL, Double.MAX_VALUE, 1);
        SpinnerValueFactory<Double> yScaleValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_NORMAL, Double.MAX_VALUE, 1);
        SpinnerValueFactory<Double> xOffsetValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, Double.MAX_VALUE, 0);
        SpinnerValueFactory<Double> yOffsetValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, Double.MAX_VALUE, 0);
        xStepSpinner.setValueFactory(xScaleValueFactory);
        yStepSpinner.setValueFactory(yScaleValueFactory);
        xOriginSpinner.setValueFactory(xOffsetValueFactory);
        yOriginSpinner.setValueFactory(yOffsetValueFactory);
        functionTextBox.textProperty().addListener((observable, oldValue, newValue) ->
                isFunctionChanged = true
        );

    }

    @FXML
    private void plot() {
        clearCanvas();
        if (isFunctionChanged) {
            String functionStr = functionTextBox.getText();
            try {
                function = parser.parse(functionStr);
                if (function.getVariables().length != 1 ||
                        !function.getVariables()[0].equals("x"))
                    throw new Exception("There must be single variable called 'x'");
                isFunctionChanged = false;
            } catch (Exception ex) {
                isFunctionChanged = true;
                showError("Failed to parse function", ex.getMessage());
                return;
            }
        }
        double canvasWidth = plotCanvas.getWidth();
        double canvasHeight = plotCanvas.getHeight();
        double xStep = 1. / xStepSpinner.getValue();
        double yStep = 1. / yStepSpinner.getValue();
        double xOrigin = (xOriginSpinner.getValue() - canvasWidth / 2) * xStep;
        double yOrigin = (yOriginSpinner.getValue() - canvasHeight / 2) * yStep;

        GraphicsContext gc = plotCanvas.getGraphicsContext2D();
        double x0 = canvasWidth / 2 - xOriginSpinner.getValue();
        double y0 = canvasHeight / 2 - yOriginSpinner.getValue();
        gc.setStroke(Color.BLACK);
        gc.strokeLine(x0, 0, x0, canvasHeight);
        gc.strokeLine(0, canvasHeight - y0, canvasWidth, canvasHeight - y0);
        gc.strokeText("O", x0 + 2, canvasHeight - y0 + 11);
        gc.setStroke(Color.rgb(9, 91, 104));
        int count = (int) canvasWidth + 1;
        try {
            Point2D[] points = calculatePoints(function, xStep, xOrigin, yStep, yOrigin, count);
            for (int i = 1; i < points.length; i++)
                gc.strokeLine(i, canvasHeight - points[i].getY(), i - 1, canvasHeight - points[i - 1].getY());
        } catch (Exception ex) {
            isFunctionChanged = true;
            showError("Failed on evaluate function", ex.getMessage());
            return;
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText("Error message: " + message);
        alert.showAndWait().ifPresent(rs -> alert.close());
    }

    private Point2D[] calculatePoints(MathExpression expression,
                                      double xStep,
                                      double xOrigin,
                                      double yStep,
                                      double yOrigin,
                                      int count) throws Exception {
        Point2D[] result = new Point2D[count];
        for (int i = 0; i < count; i++) {
            double x = xOrigin + xStep * i;
            expression.setVariableValue("x", x);
            double y = (expression.evaluate() - yOrigin) / yStep;
            result[i] = new Point2D(x, y);
        }
        return result;
    }

    private void clearCanvas() {
        GraphicsContext gc = plotCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, plotCanvas.getWidth(), plotCanvas.getHeight());
        gc.setFill(Color.rgb(200, 200, 200));
        gc.fillRect(0, 0, plotCanvas.getWidth(), plotCanvas.getHeight());
    }
}
