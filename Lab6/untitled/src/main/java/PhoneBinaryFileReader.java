import java.io.*;

public class PhoneBinaryFileReader implements PhoneFileReader {
    private ObjectInputStream stream;

    public PhoneBinaryFileReader(String path) throws IOException {
        stream = new ObjectInputStream(new FileInputStream(path));
    }

    @Override
    public void reset() throws IOException {
        stream.reset();
    }

    @Override
    public Phone readNextPhone() throws IOException {
        int id = stream.readInt();
        String firstName = readString();
        String lastName = readString();
        String patronymic = readString();
        int number = stream.readInt();
        long intracityTime = stream.readLong();
        long intercityTime = stream.readLong();
        return new Phone(id, firstName, lastName, patronymic, number, intracityTime, intercityTime);
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

    private String readString() throws IOException {
        String result = "";
        char c;
        do {
            c = stream.readChar();
            if (c != '\0')
                result += c;
        } while (c != '\0');
        return result;
    }
}
