package com.lvn.lab5;

import java.io.File;

public interface DoubleArrayReader {
    double[] readOneDimensionalArray(File file);

    double[] readOneDimensionalArray(String path);

    double[][] readTwoDimensionalArray(File file);

    double[][] readTwoDimensionalArray(String path);
}
