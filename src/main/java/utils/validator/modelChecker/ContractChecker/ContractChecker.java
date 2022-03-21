package utils.validator.modelChecker.ContractChecker;

import contract.Contract;
import person.Person;

import java.time.LocalDate;

public class ContractChecker {

    public boolean checkId(Contract contract) {
        if (contract.getId() != null) return true;
        return false;
    }

    public boolean checkStartingDate(Contract contract) {
        if (contract.getStartingDate() != null && contract.getStartingDate() instanceof LocalDate) return true;
        return false;
    }

    public boolean checkEndingDate(Contract contract) {
        if (contract.getEndingDate() != null && contract.getEndingDate() instanceof LocalDate) return true;
        return false;
    }

    public boolean checkOwner(Contract contract) {
        if(contract.getOwner() instanceof Person && contract.getOwner() != null) return true;
        return false;
    }

    public boolean checkNumber(Contract contract) {
        if (contract.getNumber() > 0) return true;
        return false;
    }
}
