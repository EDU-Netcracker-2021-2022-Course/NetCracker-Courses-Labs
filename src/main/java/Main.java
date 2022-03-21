import contractRepository.ContractRepository;
import csvReader.ContractDataCSVReader;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ContractDataCSVReader reader = new ContractDataCSVReader();
        ContractRepository repository = new ContractRepository();
        reader.run("persons-data.csv", repository);

        repository.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }
}
