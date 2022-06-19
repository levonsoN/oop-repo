package phoneFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import phone.Phone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneJSONFileWriter implements PhoneFileWriter {
    private ObjectMapper mapper;
    private List<Phone> list;
    private File file;

    public PhoneJSONFileWriter(String filename) {
        mapper = new ObjectMapper();
        file = new File(filename);
        list = new ArrayList<>();
    }

    @Override
    public void writePhone(Phone phone) throws IOException {
        list.add(phone);
    }

    @Override
    public void close() throws IOException {
        mapper.writeValue(file, list);
    }
}
