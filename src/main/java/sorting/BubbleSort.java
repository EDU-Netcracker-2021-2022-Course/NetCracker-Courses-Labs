package sorting;

import contract.Contract;
import contractRepository.ContractRepository;

import java.util.Arrays;
import java.util.Comparator;

public class BubbleSort implements ISorter{
//    TODO: Должен ли метод сортировки возвращать новый отсортированный массив или нет?
    @Override
    public <T extends Contract> T[] sort(ContractRepository contractRepository, Comparator<T> comparator) {
    //         TODO: Можем ли мы иметь здесь доступ непосредственно к массиву хранилища?
    //         TODO: Или мы можем изменять здесь только копию исходного массива?
        T[] storage = (T[]) Arrays.copyOf(contractRepository.getArray(), contractRepository.size());

        for (int i = storage.length; i >= 2; i--){
            for (int j = 0; j < i - 1; j++) {
                if (comparator.compare(storage[j], storage[j+1]) > 0) {
                    swapElements(storage, j, j + 1);
                }
            }
        }

        return storage;
    }

    public <T> void swapElements(T[] array, int i1, int i2) {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
