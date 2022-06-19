package phoneFile;

import phone.Phone;

import java.io.Closeable;
import java.io.IOException;

public interface PhoneFileReader extends Closeable {
    public void reset() throws Exception;

    public Phone readNextPhone() throws Exception;
}
