package sorting;

import contract.Contract;
import contractRepository.ContractRepository;

import java.util.Comparator;

public interface ISorter {
    public <T extends Contract> T[] sort(ContractRepository array, Comparator<T> comparator);
}
