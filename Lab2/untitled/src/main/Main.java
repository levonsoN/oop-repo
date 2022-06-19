package main;

import phone.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Phone[] phones = createPhonesArray();
        System.out.println("Phones: ");
        printPhonesArray(phones);
        System.out.println("Phones with more intracity time than 1 sec: ");
        printPhonesArray(getPhonesWithMoreIntracityTimeThan(phones, 1));
        System.out.println("Phones with intercity time than: ");
        printPhonesArray(getPhonesWithIntercityTime(phones));
        System.out.println("Phones with number between 234 and 456: ");
        printPhonesArray(getPhonesWithNumberBetween(phones, 234, 456));
    }

    private static Phone[] getPhonesWithNumberBetween(Phone[] phones, int a, int b) {
        Phone[] result;
        List<Phone> tmp = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getNumber() > a && phones[i].getNumber() < b)
                tmp.add(phones[i]);
        result = new Phone[tmp.size()];
        tmp.toArray(result);
        return result;
    }

    private static Phone[] getPhonesWithIntercityTime(Phone[] phones) {
        Phone[] result;
        List<Phone> tmp = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getIntercityTime() > 0)
                tmp.add(phones[i]);
        result = new Phone[tmp.size()];
        tmp.toArray(result);
        return result;
    }

    private static Phone[] getPhonesWithMoreIntracityTimeThan(Phone[] phones, long intracityTime) {
        Phone[] result;
        List<Phone> tmp = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getIntracityTime() > intracityTime)
                tmp.add(phones[i]);
        result = new Phone[tmp.size()];
        tmp.toArray(result);
        return result;
    }

    private static Phone[] createPhonesArray() {
        Scanner s = new Scanner(System.in);
        int count;
        System.out.println("Enter count of phones: ");
        while (true) {
            try {
                count = s.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.");
            }
        }
        Phone[] result = new Phone[count];
        for (int i = 0; i < count; i++) {
           System.out.println("Phone #" + (i + 1));
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
                System.out.println("Enter number: ");
                int number = s.nextInt();
                System.out.println("Enter intracity time: ");
                long intracityTime = s.nextLong();
                System.out.println("Enter intercity time: ");
                long intercityTime = s.nextLong();
                result[i] = new Phone(id, firstName, lastName, patronymic, number, intracityTime, intercityTime);
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.");
                i--;
            }
        }
        s.close();
        return result;
    }

    private static void printPhonesArray(Phone[] phones) {
        for (int i = 0; i < phones.length; i++)
            System.out.println("#" + (i + 1) + " " + phones[i].toString());
        if (phones.length == 0)
            System.out.println("None.");
    }
}
