package jdbc;

import Enums.ContractType;
import Enums.Sex;
import channel.Channel;
import contract.Contract;
import contract.ContractMobile;
import contract.ContractTV;
import contract.ContractWireLine;
import contractRepository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.Person;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class DbRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(String.valueOf(DbRepository.class));
    private String url;
    private String user;
    private String password;
    private String driver;

    public DbRepository() {
        try (InputStream inputStream = DbRepository.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            url = props.getProperty("datasource.url");
            user = props.getProperty("datasource.user");
            password = props.getProperty("datasource.password");
            driver = props.getProperty("spring.datasource.driverClassName");
            createTables();
        } catch (Exception e) {
            LOGGER.error("Error to read data from database.properties", e);
        }
    }

    private void createTables() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Class.forName(driver);

            PreparedStatement dropPersonTableSt =
                    connection.prepareStatement("DROP TABLE IF EXISTS PUBLIC.PERSON CASCADE");

            PreparedStatement dropContractTableSt =
                    connection.prepareStatement("DROP TABLE IF EXISTS PUBLIC.CONTRACT CASCADE");

            PreparedStatement createPersonTableSt =
                    connection.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS PUBLIC.PERSON ( " +
                                    "id              VARCHAR PRIMARY KEY NOT NULL, " +
                                    "first_name      VARCHAR, " +
                                    "middle_name     VARCHAR, " +
                                    "last_name       VARCHAR, " +
                                    "birth_date      VARCHAR, " +
                                    "passport_series INT, " +
                                    "passport_number INT, " +
                                    "age             INT, " +
                                    "sex             VARCHAR)");

            PreparedStatement createContractTableSt =
                    connection.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS PUBLIC.CONTRACT (" +
                                    "id VARCHAR PRIMARY KEY NOT NULL," +
                                    "starting_date VARCHAR," +
                                    "ending_date VARCHAR," +
                                    "contract_number INT," +
                                    "contract_owner VARCHAR," +
                                    "FOREIGN KEY (contract_owner) REFERENCES PUBLIC.PERSON," +
                                    "contract_type VARCHAR," +
                                    "additional_info VARCHAR)");

            dropPersonTableSt.executeUpdate();
            dropContractTableSt.executeUpdate();
            createPersonTableSt.executeUpdate();
            createContractTableSt.executeUpdate();

            LOGGER.info("Tables were created!");
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
        }
    }

    public void saveRepo(ContractRepository repo) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Class.forName(driver);
            PreparedStatement preparedStatementRepo = connection.prepareStatement(
                    "INSERT INTO CONTRACT (id,starting_date,ending_date,contract_number," +
                            "contract_owner,contract_type, additional_info)" +
                            "VALUES(?,?,?,?,?,?,?);");

            PreparedStatement preparedStatementPerson = connection.prepareStatement(
                    "INSERT INTO PERSON (id, first_name, middle_name, last_name," +
                            "birth_date, passport_series, passport_number, age, sex)" +
                            "VALUES(?,?,?,?,?,?,?,?,?)");


            for (int i = 0; i < repo.size(); i++) {
                Contract contract = repo.get(i);
                Person owner = contract.getOwner();

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
                LOGGER.info("Person was saved");

                preparedStatementRepo.setString(1, contract.getId().toString());
                preparedStatementRepo.setString(2, contract.getStartingDate().toString());
                preparedStatementRepo.setString(3, contract.getEndingDate().toString());
                preparedStatementRepo.setInt(4, contract.getNumber());
                preparedStatementRepo.setString(5, owner.getId().toString());

                if (contract instanceof ContractMobile) {
                    preparedStatementRepo.setString(6, ContractType.MOBILE.name());
                    preparedStatementRepo.setString(7, ((ContractMobile) contract).getMessagesTotal() + ", " + ((ContractMobile) contract).getMinutesTotal() + ", " + ((ContractMobile) contract).getTrafficTotal());
                } else if (contract instanceof ContractWireLine) {
                    preparedStatementRepo.setString(6, ContractType.WIRELINE.name());
                    preparedStatementRepo.setString(7, Integer.toString(((ContractWireLine) contract).getConnectionSpeed()));
                } else if (contract instanceof ContractTV) {
                    preparedStatementRepo.setString(6, ContractType.TV.name());

                    String channelToDb = "";

                    for (Channel channel : ((ContractTV) contract).getChannelsPackage()) {
                        String chId = channel.getId().toString();
                        String chName = channel.getName();
                        String chSite = channel.getSiteAddress();

                        if (chName.equals("") || chName == null) {
                            chName = " ";
                        }

                        if (chSite.equals("") || chName == null) {
                            chSite = " ";
                        }

                        String ch = chId + "," + chName + "," + chSite;
                        channelToDb += ch + ";";
                    }
                    preparedStatementRepo.setString(7, channelToDb);
                }

                preparedStatementRepo.executeUpdate();
                LOGGER.info("Contract was saved");
            }
        } catch (Exception e) {
            LOGGER.error("Cannot connect to database", e);
        }
    }

    public ContractRepository restoreRepoFromDB() {
        ContractRepository repo = null;

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            Class.forName(driver);

            repo = new ContractRepository();
            PreparedStatement statementPerson = connection.prepareStatement("SELECT * FROM PERSON");
            PreparedStatement statementContract = connection.prepareStatement("SELECT * FROM CONTRACT");

            ResultSet resultSetPersons = statementPerson.executeQuery();
            ResultSet resultSetContracts = statementContract.executeQuery();

            while (resultSetContracts.next()) {
                Person person = new Person();
                Contract contract = null;
                String contractType = resultSetContracts.getString(6);

                if (contractType.equals("MOBILE")) {
                    contract = new ContractMobile();
                    contract.setType(ContractType.MOBILE);
                    String[] additionalInfo = resultSetContracts.getString(7).split(", ");
                    ((ContractMobile) contract).setMessagesTotal((short) Integer.parseInt(additionalInfo[0]));
                    ((ContractMobile) contract).setMinutesTotal((short) Integer.parseInt(additionalInfo[1]));
                    ((ContractMobile) contract).setTrafficTotal((short) Integer.parseInt(additionalInfo[2]));
                } else if (contractType.equals("WIRELINE")) {
                    contract = new ContractWireLine();
                    contract.setType(ContractType.WIRELINE);
                    ((ContractWireLine) contract).setConnectionSpeed(Integer.parseInt(resultSetContracts.getString(7)));
                } else if (contractType.equals("TV")) {
                    contract = new ContractTV();
                    contract.setType(ContractType.TV);


                    List<Channel> channels = new ArrayList<>();
                    String[] chStrings = resultSetContracts.getString(7).split(";");

                    for (String channel : chStrings) {
                        String[] channelFields = channel.split(",");

                        Channel newChannel = new Channel();
                        newChannel.setId(UUID.fromString(channelFields[0]));
                        newChannel.setName(channelFields[1]);
                        newChannel.setSiteAddress(channelFields[2]);

                        channels.add(newChannel);
                    }

                    ((ContractTV) contract).setChannelsPackage(channels);
                }

                contract.setId(UUID.fromString(resultSetContracts.getString(1)));
                contract.setStartingDate(resultSetContracts.getString(2));
                contract.setEndingDate(resultSetContracts.getString(3));
                contract.setNumber(resultSetContracts.getInt(4));

                String ownerId = resultSetContracts.getString(5);

                while (resultSetPersons.next()) {
                    if (ownerId.equals(resultSetPersons.getString(1))) {
                        person.setId(UUID.fromString(resultSetPersons.getString(1)));
                        person.setFirstName(resultSetPersons.getString(2));
                        person.setMiddleName(resultSetPersons.getString(3));
                        person.setLastName(resultSetPersons.getString(4));
                        person.setBirthDate(LocalDate.parse(resultSetPersons.getString(5)));
                        person.setPassportSeries((short) resultSetPersons.getInt(6));
                        person.setPassportNumber(resultSetPersons.getInt(7));
                        person.setAge((byte) resultSetPersons.getInt(8));
                        person.setSex(Sex.valueOf(resultSetPersons.getString(9)));

                        contract.setOwner(person);
                        break;
                    }
                }

                repo.add(contract);
            }

        } catch (Exception e) {
            LOGGER.error("Cannot connect to database", e);
        }

        return repo;
    }
}