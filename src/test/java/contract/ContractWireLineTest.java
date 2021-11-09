package contract;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonTest;

import static org.junit.jupiter.api.Assertions.*;

public class ContractWireLineTest {
    public static ContractWireLine contract;
    public static Person person;

    @BeforeAll
    public static void setUp() {
        PersonTest.setUp();
        person = PersonTest.person3;

        contract = new ContractWireLine("2019-05-10",
                "2020-05-10",
                1234567,
                person,
                500);
    }

    @Test
    void getConnectionSpeedEqualsTest() {
        Assertions.assertEquals(500, contract.getConnectionSpeed());
    }
}