public class PhoneList {
    private final int maxSize;
    private final Phone[] phones;
    private int count = 0;

    public PhoneList(int maxSize) {
        this.maxSize = maxSize;
        phones = new Phone[maxSize];
    }

    public Phone get(int index) throws Exception {
        if (index < 0 || index >= count)
            throw new Exception("Index is out of range.");
        return phones[index];
    }

    public void add(Phone phone) throws Exception {
        if (count >= maxSize)
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

    public int getMaxSize() {
        return maxSize;
    }

    public Phone[] getPhones() {
        Phone[] result = new Phone[count];
        for (int i = 0; i < count; i++)
            result[i] = phones[i];
        return result;
    }
}
