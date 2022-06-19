package com.lvn.lab6to8.phoneFile;

import com.lvn.lab6to8.phone.Phone;

import java.io.*;

public class PhoneBinaryFileWriter implements Closeable, PhoneFileWriter {
    private ObjectOutputStream stream;

    public PhoneBinaryFileWriter(String path) throws IOException {
        new File(path).delete();
        stream = new ObjectOutputStream(new FileOutputStream(path));
    }

    @Override
    public void writePhone(Phone phone) throws IOException {
        stream.writeInt(phone.getId());
        stream.writeChars(phone.getFirstName());
        stream.writeChar('\0');
        stream.writeChars(phone.getLastName());
        stream.writeChar('\0');
        stream.writeChars(phone.getPatronymic());
        stream.writeChar('\0');
        stream.writeInt(phone.getNumber());
        stream.writeChars(phone.getCity());
        stream.writeChar('\0');
        stream.writeLong(phone.getIntracityTime());
        stream.writeLong(phone.getIntercityTime());
        stream.flush();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
