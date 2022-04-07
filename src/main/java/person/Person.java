package person;

import Enums.Sex;
import jaxb.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@XmlType(name = "contract_owner")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private static int lastId = 0;

    @XmlAttribute
    private UUID id;
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "middle_name")
    private String middleName;
    @XmlElement(name = "lastname_name")
    private String lastName;
    @XmlElement(name = "birth_date")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate birthDate;
    @XmlElement(name = "passport_series")
    private short passportSeries;
    @XmlElement(name = "passport_number")
    private int passportNumber;
    @XmlElement
    private byte age;
    @XmlElement
    private Sex sex;

    /**
     *
     * @param firstName
     * @param middleName
     * @param lastName
     * @param birthDate
     * @param passportSeries
     * @param passportNumber
     */
    public Person(String firstName, String middleName, String lastName, Sex sex, String birthDate, short passportSeries, int passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM.dd.yyyy"));
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.sex = sex;
        id = UUID.randomUUID();
        setAge();
    }

    public Person() {
        id = UUID.randomUUID();
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Person.lastId = lastId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate date) {
        this.birthDate = date;
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

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void setAge() {
        age = (byte) birthDate.until(LocalDate.now()).getYears();
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFullName() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    public String getPassportData() {
        return getPassportNumber() + " " + getPassportSeries();
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
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return passportSeries == person.passportSeries && passportNumber == person.passportNumber && age == person.age && Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(middleName, person.middleName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthDate, person.birthDate) && sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, birthDate, passportSeries, passportNumber, age, sex);
    }
}
