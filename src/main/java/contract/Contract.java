package contract;

import Enums.ContractType;
import jaxb.LocalDateAdapter;
import person.Person;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@XmlType(
        name = "contract",
        propOrder = {"id", "number", "type", "owner", "startingDate", "endingDate"})
@XmlSeeAlso({ContractMobile.class, ContractTV.class, ContractWireLine.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Contract {
    protected static int lastId = 0;

    @XmlAttribute
    protected UUID id;
    @XmlElement(name = "starting_date")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    protected LocalDate startingDate;
    @XmlElement(name = "ending_date")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    protected LocalDate endingDate;
    @XmlElement(name = "contract_number")
    protected int number;
    @XmlElement(name = "contract_owner")
    protected Person owner;
    @XmlAttribute
    protected ContractType type;

    /**
     * Define parent constructor
     * @param startingDate
     * @param endingDate
     * @param number
     * @param owner
     */
    public Contract(String startingDate, String endingDate, int number, Person owner, ContractType type) {
        this.startingDate = LocalDate.parse(startingDate, DateTimeFormatter.ofPattern("MM.dd.yyyy"));
        this.endingDate = LocalDate.parse(endingDate, DateTimeFormatter.ofPattern("MM.dd.yyyy"));
        this.number = number;
        this.owner = owner;
        this.type = type;
        id = UUID.randomUUID();
    }

    public Contract() {
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    /**
     * Get contract id.
     * @return UUID object that represents contract id.
     */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Get a date of start of a contract.
     * @return LocalDate object that represents date of start of a contract.
     */
    public LocalDate getStartingDate() {
        return startingDate;
    }

    /**
     * Change a date of the start of a contract.
     * @param startingDate as a LocalDate object.
     */
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Change a date of the start of a contract.
     * @param startingDate as a String.
     */
    public void setStartingDate(String startingDate) {
        this.startingDate = LocalDate.parse(startingDate);
    }

    /**
     * Get a date of the end of a contract.
     * @return LocalDate object that represents a date of the end of a contract.
     */
    public LocalDate getEndingDate() {
        return endingDate;
    }

    /**
     * Change a date of the end of a contract.
     * @param endingDate as a LocalDate object.
     */
    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    /**
     * Change a date of the end of a contract.
     * @param endingDate as a String.
     */
    public void setEndingDate(String endingDate) {
        this.endingDate = LocalDate.parse(endingDate);
    }

    /**
     * Get a contract number.
     * @return int value represents a contract number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Change a contract number.
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get a contract owner.
     * @return Person object that represents a contract owner.
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Change a contract owner.
     * @param owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Indicates whether some other contract is "equal to" this one.
     * @param o
     * @return {@code true} if this contract is the same as the obj argument;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return getNumber() == contract.getNumber() && getId().equals(contract.getId()) && getStartingDate().equals(contract.getStartingDate()) && getEndingDate().equals(contract.getEndingDate()) && getOwner().equals(contract.getOwner());
    }

    /**
     * Returns a hash code value for the contract.
     * @return  a hash code value for this contract.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartingDate(), getEndingDate(), getNumber(), getOwner());
    }

    /**
     * Returns a string representation of the contract.
     * @return a string representation of the contract.
     */
    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", number=" + number +
                ", owner=" + owner +
                '}';
    }
}