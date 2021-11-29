package sorting;

import contract.Contract;
import contractRepository.ContractRepository;

import java.util.Comparator;

public interface ISorter {
    public <T extends Contract> void sort(T[] contracts, Comparator<T> comparator);

    public default  <T> void swapElements(T[] array, int i1, int i2) {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
