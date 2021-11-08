package person;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
// TODO: JavaDoc
public class Person {
    private static int lastId = 0;
    private final UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private final LocalDate birthDate;
    private short passportSeries;
    private int passportNumber;
    private byte age;

    public Person(String firstName, String middleName, String lastName, int birthYear, int birthMonth, int birthDay, short passportSeries, int passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        id = UUID.randomUUID();
        setAge();
    }

    public UUID getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getPassportSeries() == person.getPassportSeries() && getPassportNumber() == person.getPassportNumber() && getId().equals(person.getId()) && getFirstName().equals(person.getFirstName()) && getMiddleName().equals(person.getMiddleName()) && getLastName().equals(person.getLastName()) && getBirthDate().equals(person.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getMiddleName(), getLastName(), getBirthDate(), getPassportSeries(), getPassportNumber());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", passportSeries=" + passportSeries +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
