import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhoneHandler {
    public Phone[] sortPhonesByTotalTime(Phone[] phones, boolean desc) {
        Arrays.sort(phones, new PhoneTimeComparator(desc));
        return phones;
    }

    public Phone[] sortPhonesByName(Phone[] phones, boolean desc) {
        Arrays.sort(phones, new PhoneNameComparator(desc));
        return  phones;
    }

    public Phone[] getPhonesWithNumberBetween(Phone[] phones, int a, int b) {
        Phone[] result;
        List<Phone> tmp = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getNumber() > a && phones[i].getNumber() < b)
                tmp.add(phones[i]);
        result = new Phone[tmp.size()];
        tmp.toArray(result);
        return result;
    }

    public Phone[] getPhonesWithIntercityTime(Phone[] phones) {
        Phone[] result;
        List<Phone> tmp = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getIntercityTime() > 0)
                tmp.add(phones[i]);
        result = new Phone[tmp.size()];
        tmp.toArray(result);
        return result;
    }

    public Phone[] getPhonesWithIntracityTimeMoreThan(Phone[] phones, long intracityTime) {
        Phone[] result;
        ArrayList<Phone> tmp = new ArrayList<Phone>();
        for (int i = 0; i < phones.length; i++)
            if (phones[i].getIntracityTime() > intracityTime)
                tmp.add(phones[i]);
        result = new Phone[tmp.size()];
        tmp.toArray(result);
        return result;
    }
}
