package person;

import Enums.Sex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

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
//        FixMe: Fix parsing
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM.dd.yyyy"));
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.sex = sex;
        id = UUID.randomUUID();
        setAge();
    }

    /**
     * Get person's id.
     * @return person's id.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get person's firstname.
     * @return person's firstname.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Change person's firstname.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get person's lastname.
     * @return person's lastname.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Change person's lastname.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get person's middlename.
     * @return person's middlename.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Change person's middlename.
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Get person's fullname.
     * @return
     */
    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    /**
     * Get date of person birth.
     * @return date of person birth.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Get person's passport series.
     * @return person's passport series.
     */
    public short getPassportSeries() {
        return passportSeries;
    }

    /**
     * Change person's passport series.
     * @param passportSeries
     */
    public void setPassportSeries(short passportSeries) {
        this.passportSeries = passportSeries;
    }

    /**
     * Get person's passport number.
     * @return person's passport number.
     */
    public int getPassportNumber() {
        return passportNumber;
    }

    /**
     * Change person's passport number.
     * @param passportNumber
     */
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * Get full person's passport data.
     * @return String with full person's passport data.
     */
    public String getPassportData() {
        return Short.toString(passportSeries) + " " + Integer.toString(passportNumber);
    }

    /**
     * Get person's age.
     * @return person's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Update person's age.
     */
    public void setAge() {
        age = (byte) birthDate.until(LocalDate.now()).getYears();
    }

    /**
     * Get person's sex.
     * @return person's sex.
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Set person's sex.
     * @param sex
     */
    private void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o
     * @return {@code true} if this person is the same as the obj argument;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getPassportSeries() == person.getPassportSeries() && getPassportNumber() == person.getPassportNumber() && getId().equals(person.getId()) && getFirstName().equals(person.getFirstName()) && getMiddleName().equals(person.getMiddleName()) && getLastName().equals(person.getLastName()) && getBirthDate().equals(person.getBirthDate());
    }

    /**
     * Returns a hash code value for the person.
     * @return a hash code value for the person.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getMiddleName(), getLastName(), getBirthDate(), getPassportSeries(), getPassportNumber());
    }

    /**
     * Returns a string representation of the person.
     * @return a string representation of the person.
     */
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
