package com.lvn.lab6to8.phone;

import java.util.*;

public class PhoneHandler {
    public Phone[] sortPhonesByTotalTime(Phone[] phones, boolean desc) {
        Phone[] tmp = phones.clone();
        Arrays.sort(tmp, new PhoneTimeComparator(desc));
        return tmp;
    }

    public Phone[] sortPhonesByName(Phone[] phones, boolean desc) {
        Phone[] tmp = phones.clone();
        Arrays.sort(tmp, new PhoneNameComparator(desc));
        return tmp;
    }

    public Phone[] getPhonesByCity(Phone[] phones, String city) {
        List<Phone> result = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getCity().equals(city))
                result.add(phones[i]);
        return result.toArray(new Phone[result.size()]);
    }

    public String[] getCities(Phone[] phones) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < phones.length; i++)
            result.add(phones[i].getCity());
        return result.toArray(new String[result.size()]);
    }

    public Phone[] getPhonesWithNumberBetween(Phone[] phones, int a, int b) {
        List<Phone> result = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getNumber() > a && phones[i].getNumber() < b)
                result.add(phones[i]);
        return result.toArray(new Phone[result.size()]);
    }

    public Phone[] getPhonesWithIntercityTime(Phone[] phones) {
        List<Phone> result = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getIntercityTime() > 0)
                result.add(phones[i]);
        return result.toArray(new Phone[result.size()]);
    }

    public Phone[] getPhonesWithIntracityTimeMoreThan(Phone[] phones, long intracityTime) {
        ArrayList<Phone> result = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getIntracityTime() > intracityTime)
                result.add(phones[i]);
        return result.toArray(new Phone[result.size()]);
    }
}
