package person;

import Enums.Sex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    public static Person person1;
    public static Person person2;
    public static Person person3;

    @BeforeAll
    public static void setUp() {
        person1 = new Person("Иван",
                "Иванович",
                "Иванов",
                Sex.MALE,
                "10.22.1960",
                (short) 2208,
                123456
        );

        person2 = new Person("Константинов",
                "Петр",
                "Игоревич",
                Sex.MALE,
                "01.11.1975",
                (short) 1975,
                456789
        );

        person3 = new Person("Михайлов",
                "Игорь",
                "Игоревич",
                Sex.MALE,
                "01.30.1975",
                (short) 1975,
                456789
        );
    }

    @Test
    void getIdNotNullTest() {
        Assertions.assertNotNull(person1.getId().toString());
    }

    @Test
    void getIdInstanceOfTest() {
        Assertions.assertInstanceOf(UUID.class, person1.getId());
    }

    @Test
    void getFullNameEqualsTest() {
        Assertions.assertEquals("Иван Иванович Иванов", person1.getFullName());
    }

    @Test
    void getFullNameInstanceOfTest() {
        Assertions.assertInstanceOf(String.class, person1.getFullName());
    }

    @Test
    void getBirthDateEqualsTest() {
        Assertions.assertEquals("1960-10-22", person1.getBirthDate().toString());
    }

    @Test
    void getBirthDateInstanceOfTest() {
        Assertions.assertInstanceOf(LocalDate.class, person1.getBirthDate());
    }

    @Test
    void getPassportSeriesEqualsTest() {
        Assertions.assertEquals((short) 2208, person1.getPassportSeries());
    }

    @Test
    void getPassportSeriesInstanceOfTest() {
        Assertions.assertInstanceOf(Short.class, person1.getPassportSeries());
    }

    @Test
    void getPassportNumberEqualsTest() {
        Assertions.assertEquals(123456, person1.getPassportNumber());
    }

    @Test
    void getPassportNumberInstanceOfTest() {
        Assertions.assertInstanceOf(Integer.class, person1.getPassportNumber());
    }

    @Test
    void getPassportDataEqualsTest() {
        Assertions.assertEquals("2208 123456", person1.getPassportData());
    }

    @Test
    void getPassportDataInstanceOfTest() {
        Assertions.assertInstanceOf(String.class, person1.getPassportData());
    }

    @Test
    void getAgeEqualsTest() {
        Assertions.assertEquals(61, person1.getAge());
    }

    @Test
    void getAgeInstanceOfTest() {
        Assertions.assertInstanceOf(Integer.class, person1.getAge());
    }
}