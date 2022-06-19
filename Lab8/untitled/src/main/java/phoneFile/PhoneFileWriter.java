package phoneFile;

import phone.Phone;

import java.io.Closeable;
import java.io.IOException;

public interface PhoneFileWriter extends Closeable {
    public void writePhone(Phone phone) throws Exception;
}
