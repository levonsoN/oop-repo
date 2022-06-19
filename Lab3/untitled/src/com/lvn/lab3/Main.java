package com.lvn.lab3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        int k;
        char c;
        while (true) {
            try {
                System.out.println("Enter line: ");
                input = s.nextLine();
                System.out.println("Enter word length k: ");
                k = s.nextInt();
                System.out.println("Enter character to replace: ");
                c = s.next().charAt(0);
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input. Try again.");
            }
        }
        String result = new Replacer().replace(input, k, c);
        System.out.println("Result: ");
        System.out.println(result);
        s.close();
    }
}
