package interfaces;

import contract.Contract;

import java.util.Comparator;

public interface ISorter {
    public <T extends Contract> void sort(T[] contracts, Comparator<T> comparator);

    public default <T> void swapElements(T[] array, int i1, int i2) {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    public default <T extends Contract> T[] getNotNullElements(T[] array) {
        int counter = 0;

        for (T element : array) {
            if (null != element) {
                counter++;
            }
        }

        T[] newArray = (T[]) new Contract[counter];

        for (int i = 0; i < counter; i++) {
            newArray[i] = array[i];
        }

        return newArray;
    }
}