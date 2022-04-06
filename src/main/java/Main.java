import contractRepository.ContractRepository;
import csvReader.ContractDataCSVReader;
import jdbc.DbRepository;

public class Main {
    public static void main(String[] args) {
        ContractDataCSVReader reader = new ContractDataCSVReader();
        ContractRepository repository = new ContractRepository();
        reader.run("persons-data.csv", repository);

        DbRepository dbRepo = new DbRepository();

        dbRepo.saveRepo(repository);
    }
}
