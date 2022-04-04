import contractRepository.ContractRepository;
import csvReader.ContractDataCSVReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        ContractDataCSVReader reader = new ContractDataCSVReader();
        ContractRepository repository = new ContractRepository();
        reader.run("persons-data.csv", repository);

        ContractRepository.marshall(repository);

        ContractRepository repo2 = ContractRepository.unmarshall("repository.xml");

        System.out.println("\n\nUnmarshalled repo:\n\n" + repo2);
    }
}
