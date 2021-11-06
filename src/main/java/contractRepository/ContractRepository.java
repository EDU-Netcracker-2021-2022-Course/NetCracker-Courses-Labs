package contractRepository;

import contract.Contract;

import java.util.UUID;

public class ContractRepository {
    private UUID id;
    private int initSize = 5;
    private Contract[] contractContainer;

    public ContractRepository() {
        id = UUID.randomUUID();
        contractContainer = new Contract[initSize];
    }

    /**
     * Add new contract into repository
     * @param contract
     */
    public void add(Contract contract) {
//    TODO: Realize method
    }

    /**
     * Add several new contracts into repository
     * @param contracts
     */
    public void add(Contract[] contracts) {
//    TODO: Realize method
    }

    /**
     * Removes contract from repository by its array index
     * @param index
     * @return true if element has been deleted successfully and false if hasn't been.
     */
    public boolean remove(int index) {
//    TODO: Realize method
        return false;
    }

    /**
     * Removes contract from repository by its id
     * @param id
     * @return true if element has been deleted successfully and false if hasn't been.
     */
    public boolean remove(UUID id) {
//    TODO: Realize method
        return false;
    }

    /**
     * Return Contract object from repository by its id
     * @param id
     * @return Contract object by its id
     */
    public Contract getContract(UUID id) {
//    TODO: Realize method
        return false;
    }

    /**
     * Return Contract object from repository by its index
     * @param index
     * @return Contract object by its index
     */
    public Contract getContract(int index) {
        return (Contract) contractContainer[index];
    }

    /**
     * Get current repository size
     * @return contract repository size
     */
    public int getSize() {
        return contractContainer.length;
    }

    /**
     * Set new repository size
     * @param newSize
     */
    public void resize(int newSize) {
//    TODO: Realize method
        Contract[] newContractContainer = new Contract[newSize];
        System.arraycopy(contractContainer, 0, newContractContainer, 0, contractContainer.length);
        contractContainer = newContractContainer;
    }

    /**
     * TODO: add description
     * @return
     */
    public boolean checkSize() {
//    TODO: Realize method
        return false;
    }
}
