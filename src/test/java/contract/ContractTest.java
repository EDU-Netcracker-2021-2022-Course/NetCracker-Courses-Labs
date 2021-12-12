package contract;

import Enums.ContractType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ContractTest {
    public static Contract contract;
    public static Person person;

    @BeforeAll
    public static void setUp() {
        PersonTest.setUp();
        person = PersonTest.person1;

        contract = (Contract) new ContractWireLine("2019-05-10",
                "2020-05-10",
                1234567,
                person,
                500,
                ContractType.WIRELINE);
    }

    @Test
    public void getIdEqualsTest() {
        Assertions.assertNotNull(contract.getId());
    }

    @Test
    public void getIdInstanceOfTest() {
        Assertions.assertInstanceOf(UUID.class, contract.getId());
    }

    @Test
    void getStartingDateEqualsTest() {
        Assertions.assertEquals("2019-05-10", contract.getStartingDate().toString());
    }

    @Test
    void getStartingDateInstanceOfTest() {
        Assertions.assertInstanceOf(LocalDate.class, contract.getStartingDate());
    }

    @Test
    void getEndingDateEqualsTest() {
        Assertions.assertEquals("2020-05-10", contract.getEndingDate().toString());
    }

    @Test
    void getEndingDateInstanceOfTest() {
        Assertions.assertInstanceOf(LocalDate.class, contract.getEndingDate());
    }

    @Test
    void getNumberEqualsTest() {
        Assertions.assertEquals(1234567, contract.getNumber());
    }

    @Test
    void getNumberInstanceOfTest() {
        Assertions.assertInstanceOf(Integer.class, contract.getNumber());
    }

    @Test
    void getOwnerNotNullTest() {
        Assertions.assertNotNull(contract.getOwner());
    }

    @Test
    void getOwnerEqualsTest() {
        Assertions.assertEquals(person, contract.getOwner());
    }

    @Test
    void getOwnerInstanceOf() {
        Assertions.assertInstanceOf(Person.class, contract.getOwner());
    }
}