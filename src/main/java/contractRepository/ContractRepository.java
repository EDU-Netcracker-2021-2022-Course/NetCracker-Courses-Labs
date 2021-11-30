package contractRepository;

import contract.Contract;
import sorting.BubbleSort;
import sorting.ISorter;
import sorting.MergeSort;
import sorting.QuickSort;

import java.util.*;
import java.util.function.Predicate;

public class ContractRepository<T extends Contract> {
    private static final int INIT_SIZE = 5;
    private UUID id;
    private int pointer;
    private T[] contractContainer;
    private ISorter sorterBubble = new BubbleSort();
    private ISorter sorterMerge = new MergeSort();
    private ISorter sorterQuick = new QuickSort();

    /**
     * Constructs a new object.
     */
    public ContractRepository() {
        id = UUID.randomUUID();
        pointer = 0;
        contractContainer = (T[]) new Contract[ContractRepository.INIT_SIZE];
    }

    /**
     * Appends the contract to the end of this repository.
     * @param contract
     * @return true if this repository changed as a result of the call.
     */
    public void add(T contract) {
        if (pointer == contractContainer.length - 1) {
            resize((contractContainer.length * 3) / 2 + 1);
        }
        try {
            contractContainer[pointer++] = contract;
        } catch (NullPointerException e) {
            System.out.println("You're trying to add NULL object!");
        }
    }

    /**
     * Appends several contracts to the end of this repository.
     * @param contracts
     * @return true if this repository changed as a result of the call.
     */
    public void addAll(T[] contracts) {
        for (int i = 0; i < contracts.length; i++) {
            add(contracts[i]);
        }
    }

    /**
     * Removes the contract at the specified position in this repository. Shifts any subsequent contracts to the left.
     * @param index
     * @return the element that was removed from the list.
     */
    public T remove(int index) {
        T oldValue = null;
        try {
            Objects.checkIndex(index, size());
            oldValue = contractContainer[index];
            final int newSize;

            if ((newSize = size() - 1) > index) {
//                TODO: Check if this can be done with Arrays.copyOf()
                System.arraycopy(contractContainer, index + 1, contractContainer, index, newSize - index);
            }
            contractContainer[pointer = newSize] = null;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index of contract is out of bounds");
        }

        return oldValue;
    }

    /**
     * Removes the contract by its id in this repository. Shifts any subsequent contracts to the left.
     * @param id
     * @return the element that was removed from the list.
     */
    public T remove(UUID id) {
        T contract = null;
        for (int i = 0; i <= pointer; i++) {
            try {
                if (contractContainer[i].getId().equals(id)) {
                    remove(i);
                    contract = contractContainer[i];
                    break;
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        return contract;
    }

    /**
     * Return Contract object from repository by its id.
     * @param id
     * @return Contract object by its id.
     */
    public T get(UUID id) {
        T contract = null;
        for (int i = 0; i <= pointer; i++) {
            try {
                if (contractContainer[i].getId().equals(id)) {
                    contract = contractContainer[i];
                    break;
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        return contract;
    }

    /**
     * Return Contract object from repository by its index.
     * @param index
     * @return Contract object by its index.
     */
    public T get(int index) {
        return contractContainer[index];
    }

    /**
     * Returns the number of not null elements in this list.
     * @return the number of not null elements in this list.
     */
    public int size() {
        return pointer;
    }

    /**
     * Return the full length of this repository.
     * @return the full length of this repository.
     */
    public int getFullLength() {
        return contractContainer.length;
    }

    /**
     * Increase repository size.
     * @param newSize
     */
    public void resize(int newSize) {
        try {
            if (newSize > contractContainer.length) {
                contractContainer = Arrays.copyOf(contractContainer, newSize);
            } else {
                throw new NegativeArraySizeException();
            }
        } catch (NegativeArraySizeException e) {
            System.out.println("The size of the new array must be larger than the initial size of the array.");
        }
    }

    /**
     * Clear this contract container and set pointer to the first element.
     */
    public void clear() {
        contractContainer = (T[]) new Contract[getFullLength()];
        pointer = 0;
    }

    /**
     * Prints all contracts in this repository.
     */
    public Contract[] getArray() {
        Contract[] newArray = new Contract[size()];
        for (int i = 0; i < this.size(); i++) {
            newArray[i] = contractContainer[i];
        }
        return newArray;
    }

    /**
     * String representation of repository.
     * @return string representation of repository.
     */
    @Override
    public String toString() {
        String contractToString = "";

        for (T contract : contractContainer) {
            try {
                contractToString = contractToString + contract.toString() + "\n";
            } catch (NullPointerException e) {
                continue;
            }
        }

        return "ContractRepository{\n" +
                "id=" + id +
                ", \nContracts=[" + contractToString + "]\n" +
                "}";
    }

    public Optional<Contract> searchContract(Predicate<Contract> predicate) {
        Optional<Contract> optionalContract = Optional.empty();

        for (Contract contract : contractContainer) {
            if(predicate.test(contract)) {
                optionalContract = Optional.of(contract);
                break;
            }
        }
        return optionalContract;
    }

    public void sort(Comparator<T> comparator) {
        sorterQuick.sort(contractContainer, comparator);
    }
}
