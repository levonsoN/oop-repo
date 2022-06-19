package com.lvn.lab6to8.phoneList;

import com.lvn.lab6to8.phone.Phone;

import java.util.HashSet;
import java.util.Set;

public class SetPhoneCollection implements PhoneCollection {
    private Set<Phone> phones = new HashSet<Phone>();
    private Phone[] buffer = null;

    @Override
    public int getCount() {
        return phones.size();
    }

    @Override
    public Phone get(int index) throws Exception {
        return buffer[index];
    }

    @Override
    public void add(Phone phone) throws Exception {
        phones.add(phone);
        buffer = getBuffer();
    }

    @Override
    public void removeAt(int index) throws Exception {
        phones.remove(get(index));
        buffer = getBuffer();
    }

    @Override
    public Phone[] toArray() {
        return buffer.clone();
    }

    private Phone[] getBuffer() {
        return phones.toArray(new Phone[phones.size()]);
    }
}
