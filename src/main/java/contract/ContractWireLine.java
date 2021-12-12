package contract;

import Enums.ContractType;
import person.Person;

public class ContractWireLine extends Contract {
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
