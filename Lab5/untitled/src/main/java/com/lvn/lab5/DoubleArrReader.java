package com.lvn.lab5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DoubleArrReader implements DoubleArrayReader {
    @Override
    public double[] readOneDimensionalArray(File file) {
        try (Scanner s = new Scanner(file)) {
            int n = s.nextInt();
            double[] result = new double[n];
            for (int i = 0; i < n; i++)
                result[i] = s.nextDouble();
            return result;
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
    }

    @Override
    public double[] readOneDimensionalArray(String path) {
        return readOneDimensionalArray(new File(path));
    }

    @Override
    public double[][] readTwoDimensionalArray(File file) {
        try (Scanner s = new Scanner(file)) {
            int n = s.nextInt();
            double[][] result = new double[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    result[i][j] = s.nextDouble();
            return result;
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(String path) {
        return readTwoDimensionalArray(new File(path));
    }
}
