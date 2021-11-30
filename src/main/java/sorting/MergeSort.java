package sorting;

import contract.Contract;

import java.util.Comparator;

public class MergeSort implements ISorter{
    @Override
    public <T extends Contract> void sort(T[] contracts, Comparator<T> comparator) {
        mergeSort(contracts, contracts.length - 1, comparator);
    }

    public static <T extends Contract> void mergeSort(T[] contracts, int n, Comparator<T> comparator) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        T[] l = (T[]) new Contract[mid];
        T[] r = (T[]) new Contract[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = contracts[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = contracts[i];
        }
        mergeSort(l, mid, comparator);
        mergeSort(r, n - mid, comparator);

        merge(contracts, l, r, mid, n - mid, comparator);
    }

    public static <T extends Contract> void merge(T[] contracts, T[] l, T[] r, int left, int right, Comparator comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (comparator.compare(l[i],r[j]) <= 0) {
                contracts[k++] = l[i++];
            }
            else {
                contracts[k++] = r[j++];
            }
        }
        while (i < left) {
            contracts[k++] = l[i++];
        }
        while (j < right) {
            contracts[k++] = r[j++];
        }
    }
}
