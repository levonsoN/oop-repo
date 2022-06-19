package com.lvn.lab6to8;

import com.lvn.lab6to8.menu.*;
import com.lvn.lab6to8.phone.*;
import com.lvn.lab6to8.phoneFile.*;
import com.lvn.lab6to8.phoneList.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "C:\\db";

        PhoneDbConsoleMenu menu = new PhoneDbConsoleMenu();
        PhoneHandler phoneHandler = new PhoneHandler();

        menu.printTitle();
        PhoneDbConsoleMenuDbType dbType = menu.askDbType();
        PhoneDbConsoleMenuCollectionType listType = menu.askCollectionType();
        PhoneCollection collection = null;
        try {
            collection = readCollection(listType, dbType, filename);
        } catch (Exception e) {
            System.out.println("readCollection failed with error: " + e.getMessage());
            return;
        }

        loop:
        while (true) {
            PhoneDbConsoleMenuOption option = menu.askOption();
            switch (option) {
                case Print:
                    menu.printPhones(collection.toArray(), "Phones: ");
                    break;
                case Add:
                    Phone newPhone = menu.readPhone("New Phone: ");
                    try {
                        collection.add(newPhone);
                    } catch (Exception e) {
                        System.out.println("Adding failed with error: " + e.getMessage());
                    }
                    break;
                case Remove:
                    int index = menu.readPhoneNumber(collection.getCount());
                    try {
                        collection.removeAt(index);
                    } catch (Exception e) {
                        System.out.println("Removing failed with error: " + e.getMessage());
                    }
                    menu.printLn("Success.");
                    break;
                case Filter1:
                    int time = menu.readMinIntracityTime();
                    menu.printPhones(
                            phoneHandler.getPhonesWithIntracityTimeMoreThan(collection.toArray(), time),
                            String.format("Phones with intracity time more then %d: ", time));
                    break;
                case Filter2:
                    menu.printPhones(phoneHandler.sortPhonesByName(
                                    phoneHandler.getPhonesWithIntercityTime(collection.toArray()),
                                    false),
                            "Phones with intercity time sorted by name: ");
                    break;
                case Filter3:
                    int a = menu.readInt("Enter lower bound A: ");
                    int b = menu.readInt("Enter lower bound B: ");
                    menu.printPhones(
                            phoneHandler.getPhonesWithNumberBetween(collection.toArray(), a, b),
                            String.format("Phones with number between %d and %d: ", a, b));
                    break;
                case Filter4:
                    menu.printPhones(phoneHandler.sortPhonesByTotalTime(collection.toArray(), false),
                            "Phones sorted by total time: ");
                    break;
                case Filter5:
                    menu.printCities(phoneHandler.getCities(collection.toArray()));
                    break;
                case Filter6:
                    String[] cities = phoneHandler.getCities(collection.toArray());
                    Phone[] phones = collection.toArray();
                    for (int i = 0; i < cities.length; i++)
                        menu.printCityPhones(phoneHandler.getPhonesByCity(phones, cities[i]), cities[i]);
                    break;
                case Exit:
                    break loop;
            }
            try {
                writePhoneList(collection, dbType, filename);
            } catch (Exception e) {
                System.out.println("Writing Phone List to db failed with error: " + e.getMessage());
            }
        }
    }

    private static void writePhoneList(PhoneCollection collection, PhoneDbConsoleMenuDbType dbType, String filename) throws Exception {
        PhoneFileWriter writer = null;
        switch (dbType) {
            case Text:
                filename += ".txt";
                writer = new PhoneTextFileWriter(filename);
                break;
            case Binary:
                filename += ".bin";
                writer = new PhoneBinaryFileWriter(filename);
                break;
            case JSON:
                filename += ".json";
                writer = new PhoneJSONFileWriter(filename);
                break;
        }
        for (int i = 0; i < collection.getCount(); i++)
            writer.writePhone(collection.get(i));
        writer.close();
    }

    private static PhoneCollection readCollection(PhoneDbConsoleMenuCollectionType listType, PhoneDbConsoleMenuDbType dbType, String filename) throws Exception {
        PhoneFileReader reader = null;
        switch (dbType) {
            case Text:
                filename += ".txt";
                createDbIfNotExist(filename);
                reader = new PhoneTextFileReader(filename);
                break;
            case Binary:
                filename += ".bin";
                createDbIfNotExist(filename);
                reader = new PhoneBinaryFileReader(filename);
                break;
            case JSON:
                filename += ".json";
                createDbIfNotExist(filename);
                reader = new PhoneJSONFileReader(filename);
                break;
        }
        Phone phone;
        PhoneCollection list = null;
        switch (listType) {
            case Array:
                list = new ArrayPhoneCollection();
                break;
            case List:
                list = new ListPhoneCollection();
                break;
            case Set:
                list = new SetPhoneCollection();
                break;
        }
        while (true) {
            try {
                if ((phone = reader.readNextPhone()) == null)
                    break;
                list.add(phone);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        reader.close();
        return list;
    }

    private static void createDbIfNotExist(String filename) throws IOException {
        File file;
        file = new File(filename);
        if (!file.exists())
            file.createNewFile();
    }
}
