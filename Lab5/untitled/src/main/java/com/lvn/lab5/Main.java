package com.lvn.lab5;

public class Main {
    public static void main(String[] args) {
        DoubleArrayReader reader = new DoubleArrReader();
        ArrProcessor processor = new ArrProcessor();
        double[] d1 = reader.readOneDimensionalArray("C:\\lab51.txt");
        double[][] d2 = reader.readTwoDimensionalArray("C:\\lab52.txt");
        if (d1 != null && d2 != null) {
            System.out.println("Arrays successfully read from file");
            System.out.println("\nMinimal element among elements with even index: " + processor.getMin(d1));
            System.out.println("Sum of elements in segment with absulet value less than 100: "
                    + processor.getSumOfSegment(d2));
        }
        else  System.out.println("Failed to read array from file.");
    }
}
