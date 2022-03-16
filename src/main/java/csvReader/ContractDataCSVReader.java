package csvReader;

import Enums.ContractType;
import Enums.Sex;
import channel.Channel;
import com.opencsv.CSVReader;
import contract.Contract;
import contract.ContractMobile;
import contract.ContractTV;
import contract.ContractWireLine;
import contractRepository.ContractRepository;
import interfaces.IReadable;
import person.Person;
import utils.validator.ContractValidator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ContractDataCSVReader implements IReadable {

    public void run(String filename, ContractRepository repo) {
        ContractValidator contractValidator = new ContractValidator();
        String csvFileName = "src/main/resources/" + filename;

        List<String[]> contractsData = getDataFromCSV(csvFileName);

        for (String[] contractsDatum : contractsData) {
            Contract contract = null;
            String firstname = getName(contractsDatum[1]);
            String middleName = getName(contractsDatum[0]);
            String lastName = getName(contractsDatum[2]);
            int contractNumber = getContractNumber(contractsDatum[7]);
            Sex sex = getSex(contractsDatum[3]);
            String birthDate = getDate(contractsDatum[4]);
            String contractStartingDate = getDate(contractsDatum[8]);
            String contractEndingDate = getDate(contractsDatum[9]);
            ContractType contractType = getContractType(contractsDatum[10]);

            Person person = new Person(
                    firstname,
                    middleName,
                    lastName,
                    sex,
                    birthDate,
                    getPassSeries(contractsDatum[5]),
                    getPassNumber(contractsDatum[6])
            );

            switch (contractType){
                case TV:
                    List<Channel> tvAdditionalInfo = getTVAdditionalInfo(contractsDatum[11]);
                    contract = new ContractTV(contractStartingDate, contractEndingDate, contractNumber, person, tvAdditionalInfo, contractType);
                    break;
                case MOBILE:
                    short[] mobileAdditionalInfo = getMobileAdditionalInfo(contractsDatum[11]);
                    contract = new ContractMobile(  contractStartingDate,
                                                    contractEndingDate,
                                                    contractNumber,
                                                    person,
                                                    mobileAdditionalInfo[0],
                                                    mobileAdditionalInfo[1],
                                                    mobileAdditionalInfo[2],
                                                    contractType);
                    break;
                case WIRELINE:
                    int wireLineAdditionalInfo = getWireLineAdditionalInfo(contractsDatum[11]);
                    contract = new ContractWireLine(contractStartingDate, contractEndingDate, contractNumber, person, wireLineAdditionalInfo, contractType);
                    break;
            }

            contractValidator.validate(contract);
            repo.add(contract);
        }
    }

    @Override
    public List<String[]> getDataFromCSV(String filename) {
        CSVReader csvReader = null;
        List<String[]> dataFromCSV = new ArrayList<>();

        try {
            csvReader = new CSVReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.printf("File %d not found! Check the filename!");
            e.printStackTrace();
        }

        try {
            String[] nextLine;
            int headLength = 0;

            if ((nextLine = csvReader.readNext()) != null) {
                headLength = nextLine.length;
                if (isDateValid(nextLine[4])) {
                    dataFromCSV.add(nextLine);
                }
            }

            while((nextLine = csvReader.readNext()) != null) {
                if (nextLine != null && nextLine.length == headLength) {
                    dataFromCSV.add(nextLine);
                } else {
                    System.out.printf("Line %s wasn't added, because there are wrong amount of elements. Expected %s but were added %s. \n", Arrays.toString(nextLine), headLength, nextLine.length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataFromCSV;
    }

    private String getName(String name) {
        if (isNameValid(name)) {
            return name;
        }

        return null;
    }

    private String getDate(String date) {
        if (isDateValid(date)) {
            return date;
        } else return null;
    }

    private Sex getSex(String sex) {
        Sex personSex = null;

        if (!sex.equals(null) && sex.length() != 0 && isSexValid(sex)) {
            if (sex.toLowerCase(Locale.ROOT).equals("мужской")) {
                personSex = Sex.MALE;
            } else if (sex.toLowerCase(Locale.ROOT).equals("женский")) {
                personSex = Sex.FEMALE;
            }
        }

        return personSex;
    }

    private int getPassNumber(String number) {
        if (!number.equals(null) && number.length() != 0 && isPasNumberValid(number)) {
            return Integer.parseInt(number);
        } else return -1;
    }

    private short getPassSeries(String number) {
        if (!number.equals(null) && number.length() != 0 && isPasSeriesValid(number)) {
            return Short.parseShort(number);
        } else return -1;
    }
    
    private ContractType getContractType(String type) {
        ContractType cType = null;

        if (type.equals(null) && type.length() == 0) {
            return cType;
        }

        switch (type.toLowerCase(Locale.ROOT)) {
            case "mobile" :
                cType = ContractType.MOBILE;
                break;
            case "tv" :
                cType = ContractType.TV;
                break;
            case "wireline" :
                cType = ContractType.WIRELINE;
                break;
        }

        return cType;
    }

    private int getContractNumber(String number) {
        if (number.matches("^[0-9]{8}$")) {
            return Integer.parseInt(number);
        } else return 0;
    }

    private List<Channel> getTVAdditionalInfo(String additionalInfo) {
        List<Channel> channelList = new ArrayList<>();
        String[] channelNames = getAdditionalInfoArray(additionalInfo);

        for (String channelName : channelNames) {
            channelList.add(new Channel(channelName));
        }

        return channelList;
    }

    private int getWireLineAdditionalInfo(String additionalInfo) {
        String[] array = getAdditionalInfoArray(additionalInfo);
        int addInfo = 0;
        if (array.length == 1) {
            addInfo = Integer.parseInt(array[0]);
        } else {
            System.out.printf("Additional info %s has to many elements for wireline contract type.", additionalInfo);
        }
        return addInfo;
    }

    private short[] getMobileAdditionalInfo(String additionalInfo) {
        short[] addInfo = {0, 0, 0};
        String[] array = getAdditionalInfoArray(additionalInfo);

        if (array.length == 3) {
            for (int i = 0; i < array.length; i++) {
                addInfo[i] = Short.parseShort(array[i]);
            }
        } else {
            System.out.printf("Additional info %s has to many elements for mobile contract type.", additionalInfo);
        }

        return addInfo;
    }

    private String[] getAdditionalInfoArray(String additionalInfo) {
        return additionalInfo.replace("{","").replace("}","").split(";");
    }

    private boolean isNameValid(String name) {
        boolean status = false;
        String regexp = "^[^\\s]+,?(\\s[^\\s]+)*$";

        if (name.length() > 0 &&
            !name.equals(null) &&
            name.matches(regexp)) {
                status = true;
        }

        return status;
    }

    private boolean isSexValid(String sex) {
        return (sex.equals("Мужской") || sex.equals("Женский")) ? true : false;
    }

    private boolean isDateValid(String date) {
        String DATE_FORMAT = "MM.dd.yyyy";

        if (date.length() == 0 || date.equals("")) {
            return false;
        }

        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isPasSeriesValid(String date) {
        return date.matches("^[0-9]{4}$");
    }

    private boolean isPasNumberValid(String date) {
        return date.matches("^[0-9]{6}$");
    }
}
