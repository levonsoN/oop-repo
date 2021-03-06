package com.lvn.lab2.phone;


public class Phone {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int number;
    private long intracityTime;
    private long intercityTime;

    public Phone(int id, String firstName, String lastName, String patronymic, int number, long intracityTime, long intercityTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.number = number;
        this.intracityTime = intracityTime;
        this.intercityTime = intercityTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getIntracityTime() {
        return intracityTime;
    }

    public void setIntracityTime(long intracityTime) {
        this.intracityTime = intracityTime;
    }

    public long getIntercityTime() {
        return intercityTime;
    }

    public void setIntercityTime(int intercityTime) {
        this.intercityTime = intercityTime;
    }

    @Override
    public String toString() {
        return String.format("ID: %d; FirstName: %s; LastName: %s; Patronymic: %s; Number: %d; Intracity Time: %d sec; Intercity Time: %d sec.",
                id, firstName, lastName, patronymic, number, intracityTime, intercityTime);
    }
}
