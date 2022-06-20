package com.lvn.lab6to8.phone;

import java.util.Comparator;

public class PhoneTimeComparator implements Comparator<Phone> {
    private long isDesc;

    public PhoneTimeComparator(boolean isDesc) {
        this.isDesc = isDesc ? -1 : 1;
    }
    @Override
    public int compare(Phone o1, Phone o2) {
        long res = (o1.getIntercityTime() - o2.getIntercityTime() + o1.getIntracityTime() - o2.getIntracityTime());
        return (int) (isDesc * res);
    }

    public boolean isDesc() {
        return isDesc == -1;
    }
}
