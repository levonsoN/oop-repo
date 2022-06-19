package phoneList;

import phone.Phone;

public interface PhoneCollection {
    public int getCount();

    public Phone get(int index) throws Exception;

    public void add(Phone phone) throws Exception;

    public void removeAt(int index) throws Exception;

    public Phone[] toArray();
}
