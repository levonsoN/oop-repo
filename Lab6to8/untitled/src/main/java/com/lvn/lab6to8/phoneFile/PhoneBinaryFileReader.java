package com.lvn.lab6to8.phoneFile;

import com.lvn.lab6to8.phone.Phone;

import java.io.*;

public class PhoneBinaryFileReader implements PhoneFileReader {
    private ObjectInputStream stream;

    public PhoneBinaryFileReader(String path) throws IOException {
        try {
            stream = new ObjectInputStream(new FileInputStream(path));
        } catch (EOFException ignored) {
            stream = null;
        }
    }

    @Override
    public void reset() throws IOException {
        if (stream != null) stream.reset();
    }

    @Override
    public Phone readNextPhone() throws IOException {
        if (stream == null)
            return null;
        try {
            int id = stream.readInt();
            String firstName = readString();
            String lastName = readString();
            String patronymic = readString();
            int number = stream.readInt();
            String city = readString();
            long intracityTime = stream.readLong();
            long intercityTime = stream.readLong();
            return new Phone(id, firstName, lastName, patronymic, number, city, intracityTime, intercityTime);
        } catch (EOFException e) {
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        if (stream != null) stream.close();
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
