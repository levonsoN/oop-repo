import java.util.Comparator;

public class PhoneTimeComparator implements Comparator<Phone> {
    private int isDesc;

    public PhoneTimeComparator(boolean isDesc) {
        this.isDesc = isDesc ? -1 : 1;
    }
    @Override
    public int compare(Phone o1, Phone o2) {
        return (int) (isDesc * (o1.getIntercityTime() - o2.getIntercityTime() + o1.getIntracityTime() - o2.getIntracityTime()));
    }

    public boolean isDesc() {
        return isDesc == -1;
    }
}
