package com.lvn.lab6to8.phoneFile;

import com.lvn.lab6to8.phone.Phone;

import java.io.Closeable;

public interface PhoneFileReader extends Closeable {
    public void reset() throws Exception;

    public Phone readNextPhone() throws Exception;
}
