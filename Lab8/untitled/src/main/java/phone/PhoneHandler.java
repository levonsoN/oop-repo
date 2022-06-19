package phone;

import java.util.*;

public class PhoneHandler {
    public Phone[] sortPhonesByTotalTime(Phone[] phones, boolean desc) {
        Arrays.sort(phones, new PhoneTimeComparator(desc));
        return phones;
    }

    public Phone[] sortPhonesByName(Phone[] phones, boolean desc) {
        Arrays.sort(phones, new PhoneNameComparator(desc));
        return phones;
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
        for (int i = 0; i < phones.length; i++) {
            result.add(phones[i].getCity());
            System.out.println(phones[i].getCity());
        }
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
