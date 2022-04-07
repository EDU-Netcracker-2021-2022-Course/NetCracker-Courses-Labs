import contractRepository.ContractRepository;
import csvReader.ContractDataCSVReader;
import jdbc.DbRepository;

public class Main {
    public static void main(String[] args) {
        ContractDataCSVReader reader = new ContractDataCSVReader();
        ContractRepository initialRepository = new ContractRepository();
        reader.run("persons-data.csv", initialRepository);

        DbRepository dbRepo = new DbRepository();

        dbRepo.saveRepo(initialRepository);

        ContractRepository restoredRepoFromDB = dbRepo.restoreRepoFromDB();

        System.out.println("\nInitial repository:\n" + initialRepository);


        System.out.println("\nRestored repository from DB:\n" + restoredRepoFromDB);
    }
}
