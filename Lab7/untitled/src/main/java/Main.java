import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String filename = "C:\\db";

        PhoneDbConsoleMenu menu = new PhoneDbConsoleMenu();
        PhoneHandler phoneHandler = new PhoneHandler();

        menu.printTitle();
        PhoneDbConsoleMenuDbType dbType = menu.askIsText();
        List<Phone> list = readList(dbType, filename);

        loop:
        while (true) {
            PhoneDbConsoleMenuOption option = menu.askOption();
            switch (option) {
                case Print:
                    menu.printPhones(getPhones(list), "Phones: ");
                    break;
                case Add:
                    Phone newPhone = menu.readPhone("New Phone: ");
                    list.add(newPhone);
                    break;
                case Remove:
                    int index = menu.readPhoneNumber(list.size());
                    list.remove(index);
                    menu.printLn("Success.");
                    break;
                case Filter1:
                    int time = menu.readMinIntracityTime();
                    menu.printPhones(
                            phoneHandler.getPhonesWithIntracityTimeMoreThan(getPhones(list), time),
                            String.format("Phones with intracity time more then %d: ", time));
                    break;
                case Filter2:
                    menu.printPhones(phoneHandler.sortPhonesByName(
                                    phoneHandler.getPhonesWithIntercityTime(getPhones(list)),
                                    false),
                            "Phones with intercity time sorted by name: ");
                    break;
                case Filter3:
                    int a = menu.readInt("Enter lower bound A: ");
                    int b = menu.readInt("Enter lower bound B: ");
                    menu.printPhones(
                            phoneHandler.getPhonesWithNumberBetween(getPhones(list), a, b),
                            String.format("Phones with number between %d and %d: ", a, b));
                    break;
                case Filter4:
                    menu.printPhones(phoneHandler.sortPhonesByTotalTime(getPhones(list), false),
                            "Phones sorted by total time: ");
                    break;
                case Exit:
                    break loop;
            }
            writePhoneList(list, dbType, filename);
        }
    }

    private static void writePhoneList(List<Phone> list, PhoneDbConsoleMenuDbType dbType, String filename) throws Exception {
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
        for (int i = 0; i < list.size(); i++)
            writer.writePhone(list.get(i));
        writer.close();
    }

    private static Phone[] getPhones(List<Phone> list) {
        Phone[] result = new Phone[list.size()];
        return list.toArray(result);
    }

    private static List<Phone> readList(PhoneDbConsoleMenuDbType dbType, String filename) throws Exception {
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
        List<Phone> list = new ArrayList<Phone>();
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
