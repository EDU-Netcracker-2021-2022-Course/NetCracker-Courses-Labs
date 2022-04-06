package jdbc;

import Enums.ContractType;
import contract.Contract;
import contract.ContractMobile;
import contract.ContractTV;
import contract.ContractWireLine;
import contractRepository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.Person;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class DbRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(String.valueOf(DbRepository.class));

    private String url;
    private String user;
    private String password;
    private String driver;

    public DbRepository() {
        try (InputStream inputStream = DbRepository.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            url = props.getProperty("datasource.url");
            user = props.getProperty("datasource.user");
            password = props.getProperty("datasource.password");
            driver = props.getProperty("spring.datasource.driverClassName");

            createTables();

        } catch (IOException e) {
            LOGGER.error("Error to read data from database.properties", e);
        }
    }

    private void createTables() {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Database was created.");
            Statement statementCreatePersonTable = connection.createStatement();
            Statement statementCreateContractTable = connection.createStatement();
            Statement statementShowTables = connection.createStatement();

            String createPersonTable =
                    "CREATE MEMORY TABLE IF NOT EXISTS person (" +
                            "id              VARCHAR(255) PRIMARY KEY," +
                            "first_name      VARCHAR(255)," +
                            "middle_name     VARCHAR(255)," +
                            "last_name       VARCHAR(255)," +
                            "birth_date      VARCHAR(255)," +
                            "passport_series INT," +
                            "passport_number INT," +
                            "age             INT," +
                            "sex             VARCHAR(255))";

            String createContractTable =
                    "CREATE MEMORY TABLE IF NOT EXISTS contract (" +
                            "id              varchar(255) PRIMARY KEY NOT NULL," +
                            "starting_date   VARCHAR(255)," +
                            "ending_date     VARCHAR(255)," +
                            "contract_number INT," +
                            "contract_owner  VARCHAR(255)," +
//                            "FOREIGN KEY (contract_owner) REFERENCES PERSON," +
                            "contract_type   VARCHAR(255)," +
                            "additional_info VARCHAR(255))";

            String showTables = "SHOW TABLES";

            statementCreateContractTable.executeUpdate(createContractTable);
            statementCreatePersonTable.executeUpdate(createPersonTable);
            statementShowTables.execute(showTables);

            LOGGER.info("Tables were created!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void saveRepo(ContractRepository repo) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatementRepo = connection.
                    prepareStatement("INSERT INTO contract (id,starting_date,ending_date,contract_number," +
                            "contract_owner,contract_type, additional_info)" +
                            "VALUES(?,?,?,?,?,?,?);");

            PreparedStatement preparedStatementPerson = connection.
                    prepareStatement("INSERT INTO person (id, first_name, middle_name, last_name," +
                            "birth_date, passport_series, passport_number, age, sex)" +
                            "VALUES(?,?,?,?,?,?,?,?,?)");

            for (int i = 0; i < repo.size(); i++) {
                Contract contract = repo.get(i);
                Person owner = contract.getOwner();

                preparedStatementRepo.setString(1, contract.getId().toString());
                preparedStatementRepo.setString(2, contract.getStartingDate().toString());
                preparedStatementRepo.setString(3, contract.getEndingDate().toString());
                preparedStatementRepo.setInt(4, contract.getNumber());
                preparedStatementRepo.setString(5, owner.getId().toString());

                if (contract instanceof ContractMobile) {
                    preparedStatementRepo.setString(6, ContractType.MOBILE.name());
                    preparedStatementRepo.setString(7, ((ContractMobile) contract).getMessagesTotal() + ", " +
                            ((ContractMobile) contract).getMinutesTotal() + ", " +
                            ((ContractMobile) contract).getTrafficTotal());
                } else if (contract instanceof ContractWireLine) {
                    preparedStatementRepo.setString(6, ContractType.WIRELINE.name());
                    preparedStatementRepo.setString(7, Integer.toString(((ContractWireLine) contract).getConnectionSpeed()));
                } else if (contract instanceof ContractTV) {
                    preparedStatementRepo.setString(6, ContractType.TV.name());
                    preparedStatementRepo.setString(7, ((ContractTV) contract).getChannelsPackage().toString());
                }

                preparedStatementRepo.executeUpdate();
                LOGGER.info("Contracts were saved");

                preparedStatementPerson.setString(1, owner.getId().toString());
                preparedStatementPerson.setString(2, owner.getFirstName());
                preparedStatementPerson.setString(3, owner.getMiddleName());
                preparedStatementPerson.setString(4, owner.getLastName());
                preparedStatementPerson.setString(5, owner.getBirthDate().toString());
                preparedStatementPerson.setInt(6, owner.getPassportSeries());
                preparedStatementPerson.setInt(7, owner.getPassportNumber());
                preparedStatementPerson.setInt(8, owner.getAge());
                preparedStatementPerson.setString(9, owner.getSex().toString());

                preparedStatementPerson.executeUpdate();
                LOGGER.info("Persons were saved");
            }
        } catch (Exception e) {
            LOGGER.error("Cannot connect to database", e);
        }
    }

    public ContractRepository restoreRepoFromDB() {

        return null;
    }
}
