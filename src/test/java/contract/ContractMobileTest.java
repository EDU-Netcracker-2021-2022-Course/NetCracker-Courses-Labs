package contract;

import Enums.ContractType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonTest;

public class ContractMobileTest {
    public static ContractMobile contract;
    public static Person person;

    @BeforeAll
    public static void setUp() {
        PersonTest.setUp();
        person = PersonTest.person1;

        contract = new ContractMobile("2020-10-10",
                "2021-10-10",
                1789652,
                person,
                (short) 100,
                (short) 50,
                (short) 5,
                ContractType.MOBILE);
    }

    @Test
    void getMinutesTotalEqualsTest() {
        Assertions.assertEquals(100, contract.getMinutesTotal());
    }

    @Test
    void getMinutesTotalInstanceOfTest() {
        Assertions.assertInstanceOf(Short.class, contract.getMinutesTotal());
    }

    @Test
    void getMessagesTotalEqualsTest() {
        Assertions.assertEquals(50, contract.getMessagesTotal());
    }

    @Test
    void getMessagesTotalInstanceOfTest() {
        Assertions.assertInstanceOf(Short.class, contract.getMessagesTotal());
    }

    @Test
    void getTrafficTotalEqualsTest() {
        Assertions.assertEquals(5, contract.getTrafficTotal());
    }

    @Test
    void getTrafficTotalInstanceOfTest() {
        Assertions.assertInstanceOf(Short.class, contract.getTrafficTotal());
    }
}