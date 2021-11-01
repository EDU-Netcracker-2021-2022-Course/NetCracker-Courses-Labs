package Pesron;

import java.time.LocalDate;

public class Person {
    private static int lastId = 0;
    private final int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private final LocalDate birthDate;
    private short passportSeries;
    private int passportNumber;
    private byte age;

    public Person(String firstName, String middleName, String lastName, String birthDay, String birthMonth, String birthYear, short passportSeries, int passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = LocalDate.parse(birthYear + "-" + birthMonth + "-" + birthDay);
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        id = ++lastId;
        setAge();
    }

    public int getId() {
        return id;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public short getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(short passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportData() {
        return Short.toString(passportSeries) + " " + Integer.toString(passportNumber);
    }

    public byte getAge() {
        return age;
    }

    public void setAge() {
        age = (byte) birthDate.until(LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        if (getFullName() != person.getFullName()) return false;
        if (birthDate != person.birthDate) return false;
        if (!getPassportData().equals(person.getPassportData())) return false;
        return id == person.id;
    }

    public int hashCode() {
        return (getFullName()+birthDate.toString()+getPassportData()+id).hashCode() * 31;
    }

    public String toString() {
        return getFullName() + "\nID: " + id + "\nBirth date: " + birthDate.toString() + "\n" + age + " years old\n" + "Passport: " + getPassportData() + "\n\n";
    }
}
