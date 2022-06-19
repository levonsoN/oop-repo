import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PhoneJSONFileReader implements PhoneFileReader {
    private List<Phone> list;
    private int count = 0;

    public PhoneJSONFileReader(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        list = mapper.readValue(new File(filename), new TypeReference<List<Phone>>() {
        });
    }

    @Override
    public void reset() {
        count = 0;
    }

    @Override
    public Phone readNextPhone() {
        if (count >= list.size())
            return null;
        return list.get(count++);
    }

    @Override
    public void close() {
    }
}
