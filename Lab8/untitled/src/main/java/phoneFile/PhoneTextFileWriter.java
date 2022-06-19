package phoneFile;

import phone.Phone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PhoneTextFileWriter implements PhoneFileWriter {
    FileWriter writer;

    public PhoneTextFileWriter(String path) throws IOException {
        new File(path).delete();
        writer = new FileWriter(path);
    }

    public void writePhone(Phone phone) throws IOException {
        String toWrite = phone.getId() + "\n" +
                phone.getFirstName() + "\n" +
                phone.getLastName() + "\n" +
                phone.getPatronymic() + "\n" +
                phone.getNumber() + "\n" +
                phone.getCity() + "\n" +
                phone.getIntracityTime() + "\n" +
                phone.getIntercityTime() + "\n\0\n";
        writer.write(toWrite);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
