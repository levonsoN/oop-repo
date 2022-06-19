package com.lvn.lab1;

import static java.lang.Math.*;
import java.util.Date;
import java.util.Scanner;


class Program {
    public static void main(String args[]) {
        executeCalculations();
    }

    static void executeCalculations() {
        printDate();
        Input input = readInput();
        double y = calculateY(input.getX(), input.getA(), input.getB());
        double z = calculateZ(input.getX(), input.getA(), input.getB());
        printResult(input.getX(), input.getA(), input.getB(), y, z);
    }

    static void printResult(double x, double a, double b, double y, double z) {
        System.out.printf("Input: X = %1$.4f; A = %2$.4f; B = %3$.4f.\nResult: Y = %4$.4f; Z = %5$.4f\n",
                x, a, b, y, z);
    }

    static Input readInput() {
        Scanner c = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter X, A and B: ");
                Input result = new Input(c.nextDouble(), c.nextDouble(), c.nextDouble());
                c.close();
                return result;
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
        }
    }

    static void printDate() {
        System.out.printf("Today is %1$tA %1$te %1$tB\n", new Date());
    }

    static double calculateY(double x, double a, double b) {
        return pow(sin((x * x + a) * (x * x + a)), 3) - sqrt(x / b);
    }

    static double calculateZ(double x, double a, double b) {
        return x * x / a + cos(pow(x + b, 3));
    }
}