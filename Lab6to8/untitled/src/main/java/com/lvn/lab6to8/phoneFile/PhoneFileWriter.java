package com.lvn.lab6to8.phoneFile;

import com.lvn.lab6to8.phone.Phone;

import java.io.Closeable;

public interface PhoneFileWriter extends Closeable {
    public void writePhone(Phone phone) throws Exception;
}
