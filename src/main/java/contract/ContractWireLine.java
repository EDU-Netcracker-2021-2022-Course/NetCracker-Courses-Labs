package contract;

import person.Person;
// TODO: JavaDoc
public class ContractWireLine extends Contract {
    private int connectionSpeed;

    public ContractWireLine(String startingDate, String endingDate, int number, Person owner, int connectionSpeed) {
        super(startingDate, endingDate, number, owner);
        this.connectionSpeed = connectionSpeed;
    }

    public int getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(int connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }
}
