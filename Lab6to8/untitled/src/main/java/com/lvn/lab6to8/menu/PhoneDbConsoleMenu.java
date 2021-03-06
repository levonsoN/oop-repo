package com.lvn.lab6to8.menu;

import com.lvn.lab6to8.phone.Phone;

import java.util.Scanner;

public class PhoneDbConsoleMenu {
    private final static Scanner s = new Scanner(System.in);

    public void printCityPhones(Phone[] phones, String city) {
        printPhones(phones, "Phones of city " + city + ":");
    }

    public void printCities(String[] cities) {
        System.out.print("Cities:");
        for (int i = 0; i < cities.length; i++)
            System.out.print(" " + cities[i]);
        System.out.print("\n");
    }

    public Phone readPhone(String title) {
        Scanner s = new Scanner(System.in);
        System.out.println(title);
        while (true) {
            try {
                System.out.println("Enter id: ");
                int id = s.nextInt();
                s.nextLine();
                System.out.println("Enter first name: ");
                String firstName = s.nextLine();
                System.out.println("Enter last name: ");
                String lastName = s.nextLine();
                System.out.println("Enter patronymic: ");
                String patronymic = s.nextLine();
                System.out.println("Enter city: ");
                String city = s.nextLine();
                System.out.println("Enter number: ");
                int number = s.nextInt();
                System.out.println("Enter intracity time: ");
                long intracityTime = s.nextLong();
                System.out.println("Enter intercity time: ");
                long intercityTime = s.nextLong();
                return new Phone(id, firstName, lastName, patronymic, number, city, intracityTime, intercityTime);
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.");
            }
        }
    }

    public void printPhone(Phone phone, String title) {
        System.out.println(title + phone.toString());
    }

    public void printTitle() {
        System.out.println("Phones manager");
        System.out.println("================");
    }

    public PhoneDbConsoleMenuCollectionType askCollectionType() {
        System.out.println("Choose list type:");
        System.out.println("\t1 - array");
        System.out.println("\t2 - list");
        System.out.println("\t3 - set");
        while (true) {
            try {
                String c = s.nextLine();
                if (c.charAt(0) == '1')
                    return PhoneDbConsoleMenuCollectionType.Array;
                else if (c.charAt(0) == '2')
                    return PhoneDbConsoleMenuCollectionType.List;
                else if (c.charAt(0) == '3')
                    return PhoneDbConsoleMenuCollectionType.Set;
            } catch (Exception ignored) {
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    public PhoneDbConsoleMenuDbType askDbType() {
        System.out.println("Choose database:");
        System.out.println("\t1 - open text database");
        System.out.println("\t2 - open binary database");
        System.out.println("\t3 - open JSON database");
        while (true) {
            try {
                String c = s.nextLine();
                if (c.charAt(0) == '1')
                    return PhoneDbConsoleMenuDbType.Text;
                else if (c.charAt(0) == '2')
                    return PhoneDbConsoleMenuDbType.Binary;
                else if (c.charAt(0) == '3')
                    return PhoneDbConsoleMenuDbType.JSON;
            } catch (Exception ignored) {
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    public PhoneDbConsoleMenuOption askOption() {
        System.out.println("Choose option:");
        System.out.println("\t1 - print phones");
        System.out.println("\t2 - add phones");
        System.out.println("\t3 - remove phone");
        System.out.println("\t4 - filter 1");
        System.out.println("\t5 - filter 2");
        System.out.println("\t6 - filter 3");
        System.out.println("\t7 - filter 4");
        System.out.println("\t8 - filter 5");
        System.out.println("\t9 - filter 6");
        System.out.println("0 - exit");
        while (true) {
            try {
                String c = s.nextLine();
                switch (c.charAt(0)) {
                    case '1':
                        return PhoneDbConsoleMenuOption.Print;
                    case '2':
                        return PhoneDbConsoleMenuOption.Add;
                    case '3':
                        return PhoneDbConsoleMenuOption.Remove;
                    case '4':
                        return PhoneDbConsoleMenuOption.Filter1;
                    case '5':
                        return PhoneDbConsoleMenuOption.Filter2;
                    case '6':
                        return PhoneDbConsoleMenuOption.Filter3;
                    case '7':
                        return PhoneDbConsoleMenuOption.Filter4;
                    case '8':
                        return PhoneDbConsoleMenuOption.Filter5;
                    case '9':
                        return PhoneDbConsoleMenuOption.Filter6;
                    case '0':
                        return PhoneDbConsoleMenuOption.Exit;
                    default:
                        break;
                }
            } catch (Exception ignored) {
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    public void printPhones(Phone[] phones, String title) {
        System.out.println(title);
        if (phones.length == 0) {
            System.out.println("None.");
            return;
        }
        for (int i = 0; i < phones.length; i++) {
            printPhone(phones[i], "Phone #" + (i + 1) + ": ");
        }
    }

    public void printLn(String str) {
        System.out.println(str);
    }

    public int readPhoneNumber(int count) {
        while (true) {
            int index = readInt(String.format("Enter number of list (1 - %d): ", count)) - 1;
            if (index < 0 || index >= count) {
                System.out.println("Index is out of range. Try again.");
                continue;
            }
            return index;
        }
    }

    public int readMinIntracityTime() {
        while (true) {
            int index = readInt("Enter min intracity time: ");
            if (index < 0) {
                System.out.println("Time must be greater then 0. Try again.");
                continue;
            }
            return index;
        }
    }

    public int readInt(String title) {
        while (true) {
            try {
                System.out.println(title);
                int index = s.nextInt();
                s.nextLine();
                return index;
            } catch (Exception ignored) {
            }
            System.out.println("Invalid input. Try again.");
        }
    }
}
