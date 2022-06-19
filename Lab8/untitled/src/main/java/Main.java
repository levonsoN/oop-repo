import menu.*;
import phone.*;
import phoneFile.*;
import phoneList.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws Exception {
        String filename = "C:\\db";

        PhoneDbConsoleMenu menu = new PhoneDbConsoleMenu();
        PhoneHandler phoneHandler = new PhoneHandler();

        menu.printTitle();
        PhoneDbConsoleMenuDbType dbType = menu.askDbType();
        PhoneDbConsoleMenuCollectionType listType = menu.askCollectionType();
        PhoneCollection collection = readCollection(listType, dbType, filename);

        loop:
        while (true) {
            PhoneDbConsoleMenuOption option = menu.askOption();
            switch (option) {
                case Print:
                    menu.printPhones(collection.toArray(), "Phones: ");
                    break;
                case Add:
                    Phone newPhone = menu.readPhone("New phone.Phone: ");
                    collection.add(newPhone);
                    break;
                case Remove:
                    int index = menu.readPhoneNumber(collection.getCount());
                    collection.removeAt(index);
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
            writePhoneList(collection, dbType, filename);
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
