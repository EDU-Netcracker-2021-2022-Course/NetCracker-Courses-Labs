package contract;

import Enums.ContractType;
import person.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ContractWireLine extends Contract {

    @XmlElement(name = "connection_speed")
    private int connectionSpeed;

    /**
     * Constructs a new object.
     * @param startingDate
     * @param endingDate
     * @param number
     * @param owner
     * @param connectionSpeed
     */
    public ContractWireLine(String startingDate, String endingDate, int number, Person owner, int connectionSpeed, ContractType type) {
        super(startingDate, endingDate, number, owner, type);
        this.connectionSpeed = connectionSpeed;
    }

    public ContractWireLine() {

    }
    /**
     * Get available speed connection for this contract.
     * @return available speed connection for this contract.
     */
    public int getConnectionSpeed() {
        return connectionSpeed;
    }

    /**
     * Set available speed connection for this contract.
     * @param connectionSpeed
     */
    public void setConnectionSpeed(int connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public String toString() {
        return "ContractWireLine{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", number=" + number +
                ", owner=" + owner +
                ", type=" + type +
                ", connectionSpeed=" + connectionSpeed +
                '}';
    }
}
