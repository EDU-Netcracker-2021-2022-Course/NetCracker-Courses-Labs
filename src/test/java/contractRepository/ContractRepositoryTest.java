package contractRepository;

import contract.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonTest;

public class ContractRepositoryTest {
    public static Person person1;
    public static Person person2;
    public static Person person3;
    public static ContractMobile contractMobile;
    public static ContractTV contractTV;
    public static ContractWireLine contractWireLine;

    @BeforeAll
    public static void setUp() {
        PersonTest.setUp();

        ContractMobileTest.setUp();
        ContractWireLineTest.setUp();
        ContractTVTest.setUp();

        person1 = PersonTest.person1;
        person2 = PersonTest.person2;
        person3 = PersonTest.person3;

        contractMobile =    ContractMobileTest.contract;
        contractTV =        ContractTVTest.contract;
        contractWireLine =  ContractWireLineTest.contract;
    }

    @Test
    void addTest() {
        ContractRepository localRepository = new ContractRepository();
        localRepository.add(contractTV);
        localRepository.add(contractMobile);
        localRepository.add(contractWireLine);

        Assertions.assertEquals(3,localRepository.size());
    }

    @Test
    void addAllTest() {
        ContractRepository localRepository = new ContractRepository();
        Contract[] contractArray = new Contract[]{contractMobile, contractWireLine, contractTV, contractMobile, contractWireLine, contractMobile};
        localRepository.addAll(contractArray);

        Assertions.assertEquals(6,localRepository.size());
    }

    @Test
    void removeByIndexTest() {
        ContractRepository localRepository = new ContractRepository();
        Contract[] contractArray = new Contract[]{contractMobile, contractWireLine, contractTV, contractMobile, contractWireLine};
        localRepository.addAll(contractArray);

        localRepository.remove(2);

        Assertions.assertEquals(5,localRepository.size());
    }

    @Test
    void removeByIdTest() {
        ContractRepository localRepository = new ContractRepository();
        Contract[] contractArray = new Contract[]{contractMobile, contractWireLine, contractTV, contractMobile, contractWireLine};
        localRepository.addAll(contractArray);

        localRepository.remove(contractMobile.getId());

        Assertions.assertEquals(4,localRepository.size());
    }

    @Test
    void getInstanceOfTest() {
        ContractRepository localRepository = new ContractRepository();
        Contract[] contractArray = new Contract[]{contractMobile, contractWireLine, contractTV, contractMobile, contractWireLine};
        localRepository.addAll(contractArray);

        Assertions.assertInstanceOf(Contract.class, localRepository.get(1));
    }

    @Test
    void sizeTest() {
        ContractRepository localRepository = new ContractRepository();
        Contract[] contractArray = new Contract[]{contractMobile, contractWireLine, contractTV, contractWireLine, contractMobile};
        localRepository.addAll(contractArray);
        Assertions.assertEquals(5,localRepository.size());
    }
}