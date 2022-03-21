package sorting.SelectionSort;

import contract.Contract;
import interfaces.ISorter;

import java.util.Comparator;

public class SelectionSort implements ISorter {
    @Override
    public <T extends Contract> void sort(T[] contracts, Comparator<T> comparator) {
        for (int i = 0; i < contracts.length - 1; i++) {
            int minIndex = i;
            T min = contracts[i];
            for (int j = i + 1; j < contracts.length; j++) {
                if (contracts[j]!= null && comparator.compare(contracts[j], min) < 0) {
                    minIndex = j;
                    min = contracts[j];
                }
            }
            contracts[minIndex] = contracts[i];
            contracts[i] = min;
        }

        System.out.println("Repository was sorted using Selection sorting method");
    }
}
