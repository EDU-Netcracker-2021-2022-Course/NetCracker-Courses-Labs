package sorting;

import contract.Contract;

import java.util.Comparator;

public class QuickSort implements ISorter {
    @Override
    public <T extends Contract> void sort(T[] contracts, Comparator<T> comparator) {
        int begin = 0;
        int end = getNotNullElements(contracts).length - 1;
        int i = begin, j = end;

        quickSort(contracts, begin, end, comparator);

        // Recursive call for sorting left and right subparts
        if (begin < j)
            quickSort(contracts, begin, j, comparator);

        if (end > i)
            quickSort(contracts, begin, i, comparator);
    }

    private <T extends Contract> void quickSort(T[] contracts, int begin, int end, Comparator<T> comparator) {
        if (contracts.length == 0) return;

        // end sorting if there is no to sort
        if (begin - end >= 0) return;

        // choose pivot element
        int middle = begin + (end - begin) / 2;

        T pivot = null;

        pivot = contracts[middle];

        // split into subarray, with object which are less and bigger than pivot element accordingly
        int i = begin, j = end;
        while (i <= j) {

            while (comparator.compare(contracts[i], pivot) < 0) {
                i++;
            }

            while (comparator.compare(contracts[j], pivot) > 0) {
                j--;
            }

            if (i <= j) {//меняем местами
                T temp = contracts[i];
                contracts[i] = contracts[j];
                contracts[j] = temp;
                i++;
                j--;
            }
        }
    }
}