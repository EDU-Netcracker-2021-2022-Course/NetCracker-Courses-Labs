package contract;

import person.Person;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Contract {
    protected static int lastId = 0;
    protected UUID id;
    protected LocalDate startingDate;
    protected LocalDate endingDate;
    protected int number;
    protected Person owner;

    public Contract(String startingDate, String endingDate, int number, Person owner) {
        this.startingDate = LocalDate.parse(startingDate);
        this.endingDate = LocalDate.parse(endingDate);
        this.number = number;
        this.owner = owner;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = LocalDate.parse(startingDate);
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = LocalDate.parse(endingDate);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ContractMobile contract = (ContractMobile) obj;

        if (number != contract.number) return false;
        if (!owner.equals(contract.owner)) return false;
        if (startingDate != contract.startingDate || endingDate != contract.endingDate) return false;
        return id.equals(contract.id);
    }

    @Override
    public int hashCode() {
        return (number + owner.toString() + startingDate.toString() + endingDate.toString() + id.toString()).hashCode() * 31;
    }

    @Override
    public String toString() {
        return "Contract #" + number + "\nOwner: " + owner.getFullName() + "\nStart date of the contract: " + startingDate.toString() + "\nEnd date of the contract: " + endingDate;
    }
}
