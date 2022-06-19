package phone;

import java.util.Comparator;

public class PhoneNameComparator implements Comparator<Phone> {
    private int isDesc;

    public PhoneNameComparator(boolean isDesc) {
        this.isDesc = isDesc ? -1 : 1;
    }

    @Override
    public int compare(Phone o1, Phone o2) {
        int result = o1.getFirstName().compareTo(o2.getFirstName());
        if (result != 0)
            return isDesc * result;
        result = o1.getLastName().compareTo(o2.getLastName());
        if (result != 0)
            return isDesc * result;
        return isDesc * o1.getPatronymic().compareTo(o2.getPatronymic());
    }

    public boolean isDesc() {
        return isDesc == -1;
    }
}
