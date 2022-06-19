import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PhoneTextFileReader implements PhoneFileReader {
    FileReader reader;

    public PhoneTextFileReader(String path) throws FileNotFoundException {
        reader = new FileReader(path);
    }

    public void reset() throws IOException {
        reader.reset();
    }

    public Phone readNextPhone() throws IOException {
        int c;
        String phoneStr = "";
        do {
            c = reader.read();
            if (c == -1)
                return null;
            phoneStr = phoneStr + (char) c;
        } while ((char) c != '\0');
        reader.skip(1);
        String[] fields = phoneStr.split("\n");
        return new Phone(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                Integer.parseInt(fields[4]),
                Long.parseLong(fields[5]),
                Long.parseLong(fields[6])
        );
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
