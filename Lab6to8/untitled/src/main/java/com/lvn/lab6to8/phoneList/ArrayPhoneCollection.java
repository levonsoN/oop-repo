package com.lvn.lab6to8.phoneList;

import com.lvn.lab6to8.phone.Phone;

public class ArrayPhoneCollection implements PhoneCollection {
    private final Phone[] phones = new Phone[100];
    private int count = 0;

    @Override
    public Phone get(int index) throws Exception {
        if (index < 0 || index >= count)
            throw new Exception("Index is out of range.");
        return phones[index];
    }

    public void add(Phone phone) throws Exception {
        if (count >= phones.length)
            throw new Exception("List is out of space.");
        phones[count++] = phone;
    }

    public void removeAt(int index) throws Exception {
        if (index < 0 || index >= count)
            throw new Exception("Index is out of range.");
        for (int i = index; i < count - 1; i++)
            phones[i] = phones[i + 1];
        phones[--count] = null;
    }

    public int getCount() {
        return count;
    }

    public Phone[] toArray() {
        Phone[] result = new Phone[count];
        for (int i = 0; i < count; i++)
            result[i] = phones[i];
        return result;
    }
}
