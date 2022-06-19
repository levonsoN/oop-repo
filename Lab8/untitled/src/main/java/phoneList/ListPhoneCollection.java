package phoneList;

import phone.Phone;

import java.util.ArrayList;
import java.util.List;

public class ListPhoneCollection implements PhoneCollection {
    List<Phone> phones = new ArrayList<>();

    @Override
    public int getCount() {
        return phones.size();
    }

    @Override
    public Phone get(int index) throws Exception {
        return phones.get(index);
    }

    @Override
    public void add(Phone phone) throws Exception {
        phones.add(phone);
    }

    @Override
    public void removeAt(int index) throws Exception {
        phones.remove(index);
    }

    @Override
    public Phone[] toArray() {
        return phones.toArray(new Phone[phones.size()]);
    }
}
